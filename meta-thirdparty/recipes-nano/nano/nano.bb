# ------------------------------------------------------------
# Basic package metadata
# ------------------------------------------------------------
DESCRIPTION = "Nano text editor (example recipe)"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949 \
    file://COPYING.DOC;md5=ad1419ecc56e060eccf8184a87c4285f \
"

# ------------------------------------------------------------
# Source code location and checksum
# Using official GNU nano release tarball (autotools-ready)
# ------------------------------------------------------------
SRC_URI = "https://ftp.gnu.org/gnu/nano/nano-9.0.tar.xz"
SRC_URI[md5sum] = "fe956d0e4807a96d9cf78849aaf04d54"

# ------------------------------------------------------------
# Source directory after extraction
# (tarball extracts into nano-9.0/)
# ------------------------------------------------------------
S = "${WORKDIR}/nano-9.0"

# ------------------------------------------------------------
# Build dependencies
# nano requires ncurses for terminal UI
# ------------------------------------------------------------
DEPENDS = "ncurses"

# ------------------------------------------------------------
# Build system integration
# nano uses autotools + gettext + pkgconfig
# ------------------------------------------------------------
inherit gettext pkgconfig autotools

# ------------------------------------------------------------
# Package contents
# Include syntax highlighting files under /usr/share/nano
# ------------------------------------------------------------
FILES:${PN} += "${datadir}/nano"
