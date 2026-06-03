#!/bin/sh

set -eu

PROFILE_ROOT="/var/log/textbook-profile"
RUN_ID="$(date '+%Y%m%d-%H%M%S')"
OUT_DIR="${PROFILE_ROOT}/${RUN_ID}"
LATEST_LINK="${PROFILE_ROOT}/latest"

mkdir -p "${OUT_DIR}"

run_profile() {
    title="$1"
    output="$2"
    shift 2

    {
        echo "### ${title}"
        echo "# command: $*"
        echo "# captured: $(date '+%Y-%m-%dT%H:%M:%S%z')"
        echo

        if "$@"; then
            :
        else
            status="$?"
            echo
            echo "# command failed with exit status ${status}"
        fi
    } > "${OUT_DIR}/${output}" 2>&1
}

{
    echo "Textbook systemd boot profile"
    echo "Captured: $(date '+%Y-%m-%dT%H:%M:%S%z')"
    echo "Hostname: $(hostname)"
    echo "Kernel: $(uname -a)"
    echo
    echo "== systemd-analyze time =="
    systemd-analyze time || true
} > "${OUT_DIR}/summary.txt" 2>&1

run_profile "Unit startup time ranking" "blame.txt" \
    systemd-analyze blame

run_profile "Critical boot chain" "critical-chain.txt" \
    systemd-analyze critical-chain

run_profile "Per-unit security exposure" "security.txt" \
    systemd-analyze security

run_profile "Unit dependency order graph" "dot-order.dot" \
    systemd-analyze dot --order

if systemd-analyze plot > "${OUT_DIR}/boot.svg" 2> "${OUT_DIR}/boot.svg.err"; then
    rm -f "${OUT_DIR}/boot.svg.err"
fi

ln -sfn "${OUT_DIR}" "${LATEST_LINK}"

echo "systemd profile saved to ${OUT_DIR}"
