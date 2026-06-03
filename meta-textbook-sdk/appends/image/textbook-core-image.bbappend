# Add kernel-devsrc to the SDK's target toolchain packages.
# This ensures the generated SDK includes the full kernel source tree,
# allowing developers to build out-of-tree kernel modules using the SDK.
TOOLCHAIN_TARGET_TASK:append = " kernel-devsrc"

# Add additional features to the generated image.
# In this case, enable the OpenSSH server so the target system
# boots with an SSH daemon running and ready for remote access.
EXTRA_IMAGE_FEATURES += "ssh-server-openssh"

# Modern scp uses SFTP by default, so install the server-side SFTP helper
# into the target root filesystem.
IMAGE_INSTALL:append = " openssh-sftp-server"
