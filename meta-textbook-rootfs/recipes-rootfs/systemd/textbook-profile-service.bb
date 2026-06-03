# Short description of the package
DESCRIPTION = "Systemd boot profiling service"

# Inherit the systemd class to handle service installation and enablement
inherit systemd
SYSTEMD_SERVICE:${PN} = "textbook-profile.service"
SYSTEMD_AUTO_ENABLE = "enable"

# Source code location (Git repository)
SRC_URI = " \
    file://textbook-profile.service \
    file://textbook-profile.sh \
    file://LICENSE \
    "

# License information
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1c28ba02ad7e2bf38e9ad15df1c82e8d"

# Specify the source directory after extraction
S = "${WORKDIR}"

# Runtime dependencies for the package
RDEPENDS:${PN} += "systemd-analyze"

# Install step
do_install() {
    # Install the systemd service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/textbook-profile.service ${D}${systemd_system_unitdir}

    install -d ${D}${bindir}
    install -m 0755 ${S}/textbook-profile.sh ${D}${bindir}/textbook-profile.sh
}

# Specify which files to include in the final package
FILES:${PN} += " \
    ${systemd_system_unitdir}/textbook-profile.service \
    ${bindir}/textbook-profile.sh \
    "
