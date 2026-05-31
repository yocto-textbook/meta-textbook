# -----------------------------------------------------------------------------
# Enable externalsrc support
# This allows the recipe to build directly from a local source tree instead of
# fetching and unpacking sources from SRC_URI.
# -----------------------------------------------------------------------------
inherit externalsrc

# -----------------------------------------------------------------------------
# EXTERNALSRC: Path to the local source directory used for compilation.
# EXTERNALSRC_BUILD: Path used for out-of-tree builds (same as EXTERNALSRC here).
# -----------------------------------------------------------------------------
EXTERNALSRC = "${COREBASE}/../external/hello-module"
EXTERNALSRC_BUILD = "${EXTERNALSRC}"

# Use the latest commit from the external Git source tree.
SRCREV = "${AUTOREV}"

# Construct the package version using the external Git revision.
# This prevents version-going-backwards errors when using external source trees.
EXTERNALSRC_GIT_REV := "${@__import__('subprocess').check_output(['git', '-C', d.getVar('EXTERNALSRC'), 'rev-parse', '--short=10', 'HEAD'], text=True).strip()}"
PV = "${HELLO_MODULE_VERSION}+git0+${EXTERNALSRC_GIT_REV}"

# -----------------------------------------------------------------------------
# Disable the original fetch mechanism.
# Since externalsrc is used, the recipe should not fetch sources from Git.
# -----------------------------------------------------------------------------
SRC_URI = ""

# -----------------------------------------------------------------------------
# S: Source directory for the build.
# Must point to the externalsrc path so Yocto uses the local source tree.
# -----------------------------------------------------------------------------
S = "${EXTERNALSRC}"
