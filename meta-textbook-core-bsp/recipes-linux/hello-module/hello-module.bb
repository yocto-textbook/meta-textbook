# -----------------------------------------------------------------------------
# Basic metadata
# -----------------------------------------------------------------------------
SUMMARY = "Example Kernel Module"
LICENSE = "MIT"

# Yocto uses this entry to validate the license file during do_populate_lic.
# The path is relative to S (source directory).
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c28ba02ad7e2bf38e9ad15df1c82e8d"


# -----------------------------------------------------------------------------
# Build type: kernel module
# Inheriting 'module' enables Kbuild-based module compilation automatically.
# -----------------------------------------------------------------------------
inherit module


# -----------------------------------------------------------------------------
# Source code: Git repository
# AUTOREV fetches the latest commit every time the recipe is parsed.
# -----------------------------------------------------------------------------
SRC_URI = "git://github.com/yocto-textbook/hello-module.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"


# -----------------------------------------------------------------------------
# PV: Git-based recipes typically append '+git${SRCPV}' to reflect SCM versioning.
# -----------------------------------------------------------------------------
HELLO_MODULE_VERSION = "1.0"
PV = "${HELLO_MODULE_VERSION}+git${SRCPV}"


# -----------------------------------------------------------------------------
# S: Source directory
# Git fetcher unpacks sources into ${WORKDIR}/git by default.
# Explicitly set S to match the actual unpack location.
# -----------------------------------------------------------------------------
S = "${WORKDIR}/git"


# -----------------------------------------------------------------------------
# Kernel module packaging metadata
# RPROVIDES ensures the package also provides the canonical kernel-module-* name.
# KERNEL_MODULE_AUTOLOAD loads the module automatically at boot.
# -----------------------------------------------------------------------------
RPROVIDES:${PN} += "kernel-module-hello-module"
KERNEL_MODULE_AUTOLOAD += "hello-module"


# -----------------------------------------------------------------------------
# Allow the package to be empty without triggering QA errors.
# Some kernel module recipes only produce files during do_install.
# -----------------------------------------------------------------------------
ALLOW_EMPTY:${PN} = "1"
