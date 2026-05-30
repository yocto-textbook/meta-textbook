# Inherit the textbook-core-image class, which provides the standard
# base image structure used by core images (rootfs creation,
# package installation logic, filesystem handling, etc.)
inherit textbook-core-image

# ---------------------------------------------------------------------
# Image output formats and root filesystem configuration
# ---------------------------------------------------------------------

# Define the filesystem types to generate for this image.
# - tar.bz2: compressed root filesystem archive
# - ext4: standard Linux filesystem image
IMAGE_FSTYPES = " tar.bz2 ext4"

# Base size of the root filesystem in kilobytes.
IMAGE_ROOTFS_SIZE = "10240"

# Additional free space added to the root filesystem.
IMAGE_ROOTFS_EXTRA_SPACE = "10240"

# Alignment for the root filesystem size (in kilobytes).
IMAGE_ROOTFS_ALIGNMENT = "1024"

# Factor applied to estimate overhead during rootfs creation.
IMAGE_OVERHEAD_FACTOR = "1.3"

# ---------------------------------------------------------------------
# Base package installation
# ---------------------------------------------------------------------

# Core packages installed into the image by default.
# - packagegroup-core-boot: minimal boot components
# - packagegroup-base-extended: extended base utilities
# - CORE_IMAGE_EXTRA_INSTALL: user-extendable package list
CORE_IMAGE_BASE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-base-extended \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

# Additional packagegroup installation.
# This ensures the full core package set is included.
IMAGE_INSTALL += "packagegroup-textbook-core"

# ---------------------------------------------------------------------
# Language and locale configuration
# ---------------------------------------------------------------------

# Define supported locales for this image.
LINGUAS_KO_KR = "ko-kr"
LINGUAS_EN_US = "en-us"

# List of languages to generate locale data for.
IMAGE_LINGUAS = "${LINGUAS_KO_KR} ${LINGUAS_EN_US}"

# ---------------------------------------------------------------------
# User and group configuration
# ---------------------------------------------------------------------

# Define user/group variables for easier maintenance.
GROUP_NAME = "textbook"
USER_NAME  = "core"

# Password hash for the user.
# NOTE: This example uses SHA-512 via openssl passwd -6.
#       Replace 'core' with your desired password.
USER_PASSWORD = "$(openssl passwd -6 core)"

# Enable the extrausers class to allow user creation from the image recipe.
inherit extrausers

# Create the group and user using the variables defined above.
EXTRA_USERS_PARAMS = "\
    groupadd ${GROUP_NAME}; \
    useradd -p '${USER_PASSWORD}' -g ${GROUP_NAME} ${USER_NAME}; \
    "
