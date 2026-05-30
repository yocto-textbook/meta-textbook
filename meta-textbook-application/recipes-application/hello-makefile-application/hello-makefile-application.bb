# This recipe builds an executable that links against libhello-makefile.so
DESCRIPTION = "This recipe builds an executable that uses libhello-makefile.so"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c28ba02ad7e2bf38e9ad15df1c82e8d"

# Fetch the application source from the Git repository
SRC_URI = "git://github.com/yocto-textbook/hello-makefile-application.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

# Set package version and define source/build directories
HELLO_MAKEFILE_APPLICATION_VERSION = "1.0"
PV = "${HELLO_MAKEFILE_APPLICATION_VERSION}+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# Enable pkg-config support so the build system can locate hello-makefile-library
inherit pkgconfig

# Ensure hello-makefile-library is available during build (headers, .pc file, library)
DEPENDS = "hello-makefile-library"

# Compile the application using the Makefile in the source tree
do_compile() {
    oe_runmake -C ${S} O=${B}
}

# Install the built executable into the target filesystem
do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${B}/hello-makefile-application ${D}/${bindir}
}

# Package the installed executable
FILES:${PN} += "${bindir}/hello-makefile-application"
