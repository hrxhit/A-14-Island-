# Privacy (private fork: "Smart Island – Private")

This build is hardened so that privacy does not depend on trusting the code:

- **No INTERNET permission.** Android itself blocks every network call. Weather, IP/VPN location,
  GitHub update check and contributor lists are therefore unavailable in this build.
- **No sensitive-notification bypass.** `RECEIVE_SENSITIVE_NOTIFICATIONS` is not declared, so Android keeps
  OTP / sensitive notification text hidden from the app.
- **No app-data backup.** `allowBackup="false"`: nothing is copied to Google Drive or via `adb backup`.
- **No notification text in logs.** Notification titles are no longer written to logcat.
- Notification history is OFF by default and stays on-device if you turn it on.

Upstream project: https://github.com/agupta07505/SmartIsland (GPL-3.0). This fork keeps the same license.
