#
# Copyright (c) 2026
#
# SPDX-License-Identifier: MIT
#

# In zsh the value of $0 depends on the FUNCTION_ARGZERO option which is
# set by default. FUNCTION_ARGZERO, when it is set, sets $0 temporarily
# to the name of the function/script when executing a shell function or
# sourcing a script. POSIX_ARGZERO option, when it is set, exposes the
# original value of $0 in spite of the current FUNCTION_ARGZERO setting.
#
# Note: The version of zsh need to be 5.0.6 or above. Any versions below
# 5.0.6 maybe encounter errors when sourcing this script.
if [ -n "${ZSH_VERSION:-}" ]; then
	dir="${(%):-%N}"
else
	dir="${BASH_SOURCE[0]}"
fi

# identify source tree root directory
WORKSPACE_BASE=$( builtin cd "$( dirname "$dir" )" > /dev/null && pwd)

# set the build directory for the build system
BUILD_DIR=${BUILD_DIR:-build}

# backup DL_DIR to SOURCE_MIRROR for later use
function do_source_mirror() {
	local source_mirror=$(bitbake-getvar --quiet --value SOURCE_MIRROR_URL)
	local dl_dir=$(bitbake-getvar --quiet --value DL_DIR)

	source_mirror="${source_mirror#file://}"
	echo "source_mirror: ${source_mirror}"
	mkdir -p ${source_mirror}
	rsync --archive --verbose --ignore-existing --exclude '**/git2/' --exclude '*done' ${dl_dir}/ ${source_mirror}
}

# backup SSTATE_DIR to SSTATE_MIRRORS for later use
function do_sstate_mirror() {
	local sstate_mirror=$(bitbake-getvar --quiet --value SSTATE_MIRRORS)
	local sstate_cache=$(bitbake-getvar --quiet --value SSTATE_DIR)

	sstate_mirror=$(echo "${sstate_mirror}" | sed 's|.*file://||; s|/PATH||')
	echo "sstate_mirror: ${sstate_mirror}"
	mkdir -p ${sstate_mirror}
	rsync --archive --verbose --delete --exclude "*.lock" ${sstate_cache}/ ${sstate_mirror}
}

# source the build environment
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
export TEMPLATECONF=${WORKSPACE_BASE}/layers/meta-textbook/meta-textbook-core/conf/templates/default/
export MACHINE=textbook
export DISTRO=textbook-systemd-distro
source poky/oe-init-build-env ${WORKSPACE_BASE}/${BUILD_DIR}
