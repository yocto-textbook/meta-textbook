SUMMARY = "Modern and colorful command line resource monitor"
HOMEPAGE = "https://github.com/aristocratos/btop"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "virtual/crypt"

SRC_URI = "git://github.com/aristocratos/btop.git;protocol=https;branch=main"
SRCREV = "v1.4.0"

S = "${WORKDIR}/git"

# 1. Override Makefile variables to prevent it from inferring host-specific
#    user IDs, permissions, or architecture details. This ensures that the
#    build environment remains fully deterministic and isolated from the host.
EXTRA_OEMAKE = " \
    CXX='${CXX}' \
    CC='${CC}' \
    STRIP='${STRIP}' \
    ARCH='${TARGET_ARCH}' \
    PREFIX='${prefix}' \
    WARN=false \
"

do_compile() {
    oe_runmake
}

# 2. Fully override the install step to ensure a clean and reproducible
#    installation. Instead of relying on the Makefile's install target—which
#    may apply host-specific ownership or paths—we explicitly install files
#    using Yocto's standard install helpers.
do_install() {
    # Install the main executable
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/btop ${D}${bindir}/btop

    # Install themes and data files (excluding icons)
    if [ -d ${S}/themes ]; then
        install -d ${D}${datadir}/btop/themes
        install -m 0644 ${S}/themes/* ${D}${datadir}/btop/themes/
    fi
}

FILES:${PN} += " \
    ${bindir}/btop \
    ${datadir}/btop \
    "