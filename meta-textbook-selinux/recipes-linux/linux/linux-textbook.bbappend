# Include the SELinux configuration fragment to extend the base kernel options.
SRC_URI += "file://selinux.cfg"

# -----------------------------------------------------------------------------
# File Search Paths
# -----------------------------------------------------------------------------
# Prepend the local 'files' directory to ensure recipe patches and configuration
# fragments within this layer take precedence during file fetching.
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
