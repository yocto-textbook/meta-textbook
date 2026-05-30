# DESCRIPTION: Short summary of the recipe
DESCRIPTION = "Linux Kernel"

# SECTION: Category of the recipe within the metadata
SECTION = "kernel"

# LICENSE: Specifies the license under which the kernel is distributed
LICENSE = "GPLv2"

# LIC_FILES_CHKSUM: Checksum for license file validation
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# -----------------------------------------------------------------------------
# Inherit kernel build classes
# -----------------------------------------------------------------------------
inherit kernel
inherit kernel-yocto

# -----------------------------------------------------------------------------
# Source code configuration
# -----------------------------------------------------------------------------
# SRC_URI: Git repository for kernel source
# 'branch', 'name', 'tag', and 'nocheckout' are passed to the git fetcher
SRC_URI = "git://github.com/yocto-textbook/linux.git;protocol=https;branch=main"

# SRCREV: Git revision to fetch (AUTOREV pulls latest commit)
SRCREV = "${AUTOREV}"

# Additional configuration fragments for the kernel
SRC_URI += "\
    file://qemuarm64.cfg \
    file://qemuarm64-ext.cfg \
    "

# -----------------------------------------------------------------------------
# Package metadata
# -----------------------------------------------------------------------------
# PROVIDES: Declares that this recipe provides the virtual/kernel target
PROVIDES += "virtual/kernel"

# PV: Package version, using kernel version + short git revision
# SRCPV is the 7-character truncated commit hash
PV = "${LINUX_VERSION}+git${SRCPV}"

# COMPATIBLE_MACHINE: Restricts this recipe to specific machines
COMPATIBLE_MACHINE = "textbook"

# -----------------------------------------------------------------------------
# File search path
# -----------------------------------------------------------------------------
# FILESEXTRAPATHS: Adds additional directories to search for files
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
