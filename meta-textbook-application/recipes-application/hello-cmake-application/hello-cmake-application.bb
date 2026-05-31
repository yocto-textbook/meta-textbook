# This recipe builds an executable that links against libhello-cmake.so
DESCRIPTION = "This recipe builds an executable that uses libhello-cmake.so"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c28ba02ad7e2bf38e9ad15df1c82e8d"

# Fetch the application source from the Git repository
SRC_URI = "git://github.com/yocto-textbook/hello-cmake-application.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

# Set package version and define source/build directories
HELLO_CMAKE_APPLICATION_VERSION = "1.0"
PV = "${HELLO_CMAKE_APPLICATION_VERSION}+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# Enable CMake and pkg-config support so the build system can locate hello-cmake-library
inherit cmake pkgconfig

# Ensure hello-cmake-library is available during build
DEPENDS = "hello-cmake-library"
