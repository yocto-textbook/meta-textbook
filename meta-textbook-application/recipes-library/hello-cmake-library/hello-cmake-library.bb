# This recipe builds and installs the hello-cmake shared library
DESCRIPTION = "This recipe makes shared library with CMake"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c28ba02ad7e2bf38e9ad15df1c82e8d"

# Fetch the library source from the Git repository
SRC_URI = "git://github.com/yocto-textbook/hello-cmake-library.git;protocol=https;branch=main"
SRCREV = "${AUTOREV}"

# Set package version and define source/build directories
HELLO_CMAKE_LIBRARY_VERSION = "1.0"
PV = "${HELLO_CMAKE_LIBRARY_VERSION}+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# Compile and install the shared library using CMake
inherit cmake
