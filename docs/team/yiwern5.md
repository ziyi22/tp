---
  layout: default.md
  title: "Tsui Yi Wern's Project Portfolio Page"
---

### Project: EffiTrack

EffiTrack is a **desktop app for managing employees, tracking the efficiency of employees,
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EffiTrack can get your contact management tasks done faster than traditional GUI apps.

EffiTrack simplifies the task of measuring and monitoring employee efficiency for
HR departments. </br> Instead of using time-consuming and error-prone methods like manual documentation or
outdated systems, EffiTrack offers a centralized platform for easy and accurate monitoring of employee performance.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands. (e.g. assign task, mark task, reassign task etc.)
    * Credits: Reused code from AddressBook Level 4.

* **New Feature**: Added a history command that allows the user to navigate to previous commands.
    * Credits: Reused code from AddressBook Level 4.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=yiwern5&tabRepo=AY2324S2-CS2103T-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.3(trial)`, `v1.3.1` (2 releases) on GitHub
    * Managed milestones `v1.1` - `v1.4b` (7 milestones) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#47](https://github.com/AY2324S2-CS2103T-T14-1/tp/pull/47), [\#69](https://github.com/AY2324S2-CS2103T-T14-1/tp/pull/69))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `undo`, `redo`, and `history` [\#74](https://github.com/AY2324S2-CS2103T-T14-1/tp/pull/74)
        * Did cosmetic tweaks to existing documentation of features: [\#178](https://github.com/AY2324S2-CS2103T-T14-1/tp/pull/178)
    * Developer Guide:
        * Added future enhancement section.
