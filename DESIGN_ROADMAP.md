# Smart Island – Private: design roadmap

Rules for every step: no INTERNET permission, no new permissions, small compile-safe changes, one screen at a time.

| # | Step | Status |
|---|------|--------|
| 0 | Privacy hardening (no INTERNET, no sensitive-notification bypass, no backup, no title logs) | done |
| 1 | Theme presets (7 one-tap looks: iOS Glass, One UI Now, AMOLED Minimal, Aurora, Neon Cyber, Sunset, Paper Light) | done, untested on device |
| 2 | Media card: album-art backdrop, bigger artwork, progress scrubber, offline synced lyrics (embedded / local .lrc) | next |
| 3 | Call card: iOS-style answer/decline, caller avatar, in-call timer pill | planned |
| 4 | Notification cards: grouped replies, per-app accent, calmer typography | planned |
| 5 | Motion + performance pass: one spring spec, fewer recompositions, no blur when idle | planned |
| 6 | Extras: Live Activities (timer, ride, delivery, score), mic/camera privacy dots, Bluetooth device pop-up, navigation map card | ideas |

Offline lyrics note: live lyrics need either lyrics embedded in the track / a local `.lrc` file (works with no internet),
or an opt-in online build with INTERNET for a single lyrics host. The default build stays offline.
