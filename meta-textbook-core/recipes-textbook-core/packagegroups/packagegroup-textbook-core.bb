# Description of this packagegroup. This group defines the essential
# runtime components required for a minimal core system.
DESCRIPTION = "Textbook Core package group providing essential system packages"

# Inherit the packagegroup class so that this recipe becomes a package group
# and its RDEPENDS/RRECOMMENDS define the actual content.
inherit packagegroup

# Package architecture is tied to the machine, since dependencies may vary
# depending on MACHINE_FEATURES.
PACKAGE_ARCH = "${MACHINE_ARCH}"

# ---------------------------------------------------------------------
# Virtual runtime providers
# ---------------------------------------------------------------------
# These variables allow the distro to override which implementation is used
# for device management (udev, mdev, systemd-udevd, etc.) and keymap handling.
VIRTUAL-RUNTIME_dev_manager ?= "udev"
VIRTUAL-RUNTIME_keymaps ?= "keymaps"

# EFI bootloader provider. If the machine supports EFI, this package will be
# pulled in automatically.
EFI_PROVIDER ??= "grub-efi"

# ---------------------------------------------------------------------
# SysV init scripts (only used when DISTRO_FEATURES contains 'sysvinit')
# ---------------------------------------------------------------------
# This list defines which init scripts should be installed when sysvinit
# is enabled. When systemd is used, this block is ignored.
SYSVINIT_SCRIPTS = "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'rtc', '${VIRTUAL-RUNTIME_base-utils-hwclock}', '', d)} \
    modutils-initscripts \
    ${VIRTUAL-RUNTIME_initscripts} \
    "

# ---------------------------------------------------------------------
# Runtime dependencies for the packagegroup
# ---------------------------------------------------------------------
# These packages are required for a functional base system. Conditional
# dependencies are included based on DISTRO_FEATURES and MACHINE_FEATURES.
RDEPENDS:${PN} = "\
    base-files \
    base-passwd \
    ${VIRTUAL-RUNTIME_base-utils} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', '${SYSVINIT_SCRIPTS}', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'keyboard', '${VIRTUAL-RUNTIME_keymaps}', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'efi', '${EFI_PROVIDER} kernel', '', d)} \
    netbase \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${VIRTUAL-RUNTIME_update-alternatives} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS} \
    "

# ---------------------------------------------------------------------
# Recommended packages (optional but useful)
# ---------------------------------------------------------------------
# These packages are recommended for most systems but are not strictly required.
# They may be skipped if space is limited or if the system uses systemd instead
# of sysvinit.
RRECOMMENDS:${PN} = "\
    ${VIRTUAL-RUNTIME_base-utils-syslog} \
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'init-ifupdown', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit pni-names', 'ifupdown', '', d)} \
    "
