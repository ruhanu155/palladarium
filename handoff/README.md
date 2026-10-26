# How Claude and Antigravity work together

The two tools can't talk to each other directly. They share this repo instead.

1. **Pull first.** Start every session with `git pull`, then read `BOARD.md` and the top of `LOG.md`.
2. **Claim, then work.** Put your name on a `todo` task, set it to `doing`, and commit and push that claim right away.
3. **Own your files.** Work only on your task's branch and its files. If you need a change somewhere else, add a task to the board instead of editing it.
4. **Hand off.** When you open a PR, set the task to `review`. Add a LOG entry saying what changed and what's next.
5. **Ruhan decides.** Tasks owned by `ruhan` are his decisions, and no agent does them.

To hand work from Antigravity to Claude, tell Claude "check the board". To go the other way, tell Antigravity the same.
