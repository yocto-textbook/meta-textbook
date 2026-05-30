inherit externalsrc

# -----------------------------------------------------------------------------
# Use external kernel source tree
# -----------------------------------------------------------------------------
EXTERNALSRC = "${COREBASE}/../external/linux"

# Use the latest commit from the external Git source tree.
# This ensures SRCPV is generated based on the current Git revision.
SRCREV = "${AUTOREV}"

# Construct the package version using the kernel version plus the Git-based SRCPV.
# This prevents version‑going‑backwards errors when using external source trees.
PV = "${LINUX_VERSION}+git${SRCPV}"


# -----------------------------------------------------------------------------
# Kernel configuration fragments to be merged manually when using externalsrc
# -----------------------------------------------------------------------------
KERNEL_CONFIG_FRAGMENTS = "\
    ${THISDIR}/files/qemuarm64.cfg \
    ${THISDIR}/files/qemuarm64-ext.cfg \
    "

# Append selinux.cfg to SRC_URI only when the target machine declares
# the 'selinux' feature. This allows SELinux-specific configuration
# to be included conditionally based on MACHINE_FEATURES.
KERNEL_CONFIG_FRAGMENTS:append = "${@bb.utils.contains('MACHINE_FEATURES', 'selinux', ' ${THISDIR}/files/selinux.cfg', '', d)}"


# -----------------------------------------------------------------------------
# Force manual merging of kernel config fragments when externalsrc is used.
# This bypasses kernel-yocto's metadata/configme pipeline, which does not run
# when externalsrc is active.
# -----------------------------------------------------------------------------
do_configure:append() {
    # Ensure .config exists before attempting to merge fragments
    if [ -f "${B}/.config" ]; then
        bbnote "[externalsrc] Applying kernel config fragments manually..."

        # 1. Merge fragments into the existing .config using kernel's merge_config.sh
        ${S}/scripts/kconfig/merge_config.sh -m -O ${B} \
            ${B}/.config ${KERNEL_CONFIG_FRAGMENTS}

        # 2. Regenerate final .config with dependency resolution
        oe_runmake -C ${S} O=${B} olddefconfig
    else
        bberror "[externalsrc] .config file not found. Cannot apply fragments."
    fi
}
