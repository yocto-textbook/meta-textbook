## $ systemctl status textbook-profile.service
```
Jun 03 04:53:38 textbook systemd[1]: Starting Capture systemd boot profile...
Jun 03 04:53:44 textbook textbook-profile.sh[270]: systemd profile saved to /var/log/textbook-profile/20260603-045338
Jun 03 04:53:44 textbook systemd[1]: Finished Capture systemd boot profile.
~ # systemctl status textbook-profile.service > log.txt
~ # cat log.txt
* textbook-profile.service - Capture systemd boot profile
     Loaded: loaded (/usr/lib/systemd/system/textbook-profile.service; enabled; preset: enabled)
     Active: active (exited) since Wed 2026-06-03 04:53:44 UTC; 3min 17s ago
    Process: 270 ExecStart=/usr/bin/textbook-profile.sh (code=exited, status=0/SUCCESS)
   Main PID: 270 (code=exited, status=0/SUCCESS)
        CPU: 1.671s

Jun 03 04:53:38 textbook systemd[1]: Starting Capture systemd boot profile...
Jun 03 04:53:44 textbook textbook-profile.sh[270]: systemd profile saved to /var/log/textbook-profile/20260603-045338
Jun 03 04:53:44 textbook systemd[1]: Finished Capture systemd boot profile.
```

## $ journalctl -u textbook-profile.service
```
Jun 03 04:53:38 textbook systemd[1]: Starting Capture systemd boot profile...
Jun 03 04:53:44 textbook textbook-profile.sh[270]: systemd profile saved to /var/log/textbook-profile/20260603-045338
Jun 03 04:53:44 textbook systemd[1]: Finished Capture systemd boot profile.
```

## $ ls -l /var/log/textbook-profile/latest/
```
total 20
-rw-r--r--. 1 root root 101 Jun  3 04:53 blame.txt
-rw-r--r--. 1 root root   0 Jun  3 04:53 boot.svg
-rw-r--r--. 1 root root   0 Jun  3 04:53 boot.svg.err
-rw-r--r--. 1 root root 141 Jun  3 04:53 critical-chain.txt
-rw-r--r--. 1 root root 109 Jun  3 04:53 dot-order.dot
-rw-r--r--. 1 root root 105 Jun  3 04:53 security.txt
-rw-r--r--. 1 root root 212 Jun  3 04:53 summary.txt
```

## $ cat /var/log/textbook-profile/latest/blame.txt
```
### Unit startup time ranking
# command: systemd-analyze blame
# captured: 2026-06-03T04:53:39+0000
```
