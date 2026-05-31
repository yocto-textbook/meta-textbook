# Use the externalsrc class to build directly from a local source tree
inherit externalsrc

# Path to the external source directory.
# This allows Yocto to skip fetching/unpacking and instead use the
# developer’s working copy during the build.
EXTERNALSRC = "${COREBASE}/../external/hello-makefile-library"

# Separate build directory used when performing out-of-tree builds.
# Yocto will run the build steps inside this directory while still
# using the external source directory as the source tree.
EXTERNALSRC_BUILD = "${WORKDIR}/build"

# Ensure version is always higher than git-based builds
SRCREV = "${AUTOREV}"
EXTERNALSRC_GIT_REV := "${@__import__('subprocess').check_output(['git', '-C', d.getVar('EXTERNALSRC'), 'rev-parse', '--short=10', 'HEAD'], text=True).strip()}"
PV = "${HELLO_MAKEFILE_LIBRARY_VERSION}+git0+${EXTERNALSRC_GIT_REV}"
