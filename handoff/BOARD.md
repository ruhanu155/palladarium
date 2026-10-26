# Task board (shared by Claude and Antigravity)

One line per task. To claim a task, put your name in Owner and set Status to `doing`, then commit the claim **before** you start, so the other tool sees it on its next `git pull`.

Status values: `todo`, `doing`, `review` (PR open), `done`, `blocked` (say why in LOG.md).
Owner values: `claude`, `antigravity`, `ruhan`.

| # | Task | Owner | Status | Branch / PR |
| --- | --- | --- | --- | --- |
| 1 | M0 scaffold: empty mod, checks, CI, blueprint docs | claude | review | dev/lead/m0-scaffold |
| 2 | First CI build passes (open the M0 PR) | ruhan | todo | |
| 3 | Milestone 1 starts only when Ruhan says go | ruhan | todo | |
