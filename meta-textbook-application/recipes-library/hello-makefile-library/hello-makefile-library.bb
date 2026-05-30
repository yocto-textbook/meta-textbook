# This recipe builds and installs the hello-makefile shared library
DESCRIPTION = "This recipe makes shared library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c28ba02ad7e2bf38e9ad15df1c82e8d"

# Fetch the library source from the Git repository
SRC_URI = "git://github.com/yocto-textbook/hello-makefile-library.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

# Set package version and define source/build directories
HELLO_MAKEFILE_LIBRARY_VERSION = "1.0"
PV = "${HELLO_MAKEFILE_LIBRARY_VERSION}+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# Compile the shared library using the Makefile in the source tree
do_compile() {
    oe_runmake -C ${S} O=${B} LDFLAGS="${LDFLAGS}"
}

# Install the shared library, header, and pkg-config file
do_install() {
    # Install shared library and symlinks
    install -d ${D}/${libdir}
    install -m 0755 ${B}/libhello-makefile.so.1.0 ${D}/${libdir}
    ln -s libhello-makefile.so.1.0 ${D}/${libdir}/libhello-makefile.so.1
    ln -s libhello-makefile.so.1 ${D}/${libdir}/libhello-makefile.so

    # Install header file
    install -d ${D}/${includedir}
    install -m 0644 ${S}/hello-makefile-library.h ${D}/${includedir}

    # Install pkg-config metadata
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${S}/hello-makefile-library.pc ${D}${libdir}/pkgconfig/
}

# Package the shared library and development files
FILES:${PN} += "${libdir}/libhello-makefile.so.1.0 ${libdir}/libhello-makefile.so.1"
FILES:${PN}-dev += "${libdir}/libhello-makefile.so ${includedir}/hello-makefile-library.h ${libdir}/pkgconfig"
