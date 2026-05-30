# A minimal bootable image. This image includes only the essential
# components required to bring up the system and reach a usable shell.
SUMMARY = "A small image just capable of allowing a device to boot."

# Base package installation for the image.
# packagegroup-textbook-core: textbook core components
# CORE_IMAGE_EXTRA_INSTALL: user-defined additional packages
IMAGE_INSTALL = "packagegroup-textbook-core ${CORE_IMAGE_EXTRA_INSTALL}"

# Disable locale generation by default (empty list).
IMAGE_LINGUAS = " "

# License for this image recipe.
LICENSE = "MIT"

# Inherit the core-image class, which provides the standard image
# creation workflow and rootfs generation logic.
inherit core-image

# ---------------------------------------------------------------------
# Root filesystem configuration
# ---------------------------------------------------------------------

# Base size of the root filesystem in kilobytes.
IMAGE_ROOTFS_SIZE ?= "8192"
