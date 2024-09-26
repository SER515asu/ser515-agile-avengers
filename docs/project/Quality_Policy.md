# Quality Policy

## GitHub Workflow
- Branches are named with the following conventions:
  - User Story branches: `US-42-the-meaning-of-life`
  - Task Branches: `Task-43-not-the-meaning-of-life`
- Protected branches are master and dev
- One branch per User Story
- One branch per Storyless Task (only time branches are enforced for tasks)
- Extra branches as needed, but rarely
- Each User Story branch must be reviewed individually by one member before being merged into dev
  - To pass review the code review checklist must be completed as outlined in the Code Review Policy
- **Target branch must be merged into source branch before merging to resolve conflicts**
- Code needs to be reviewed by two members other than the PR submitter when merging into master
- Code reviews should follow the Code Review Template
- Code does not need to be reviewed when merging into branches other than dev or master
- PRs must be merged into dev, then dev can be merged into master, **no PR should ever be merged directly into master**
- Conflicts with destination branch must be resolved (via merging, etc.) before merging.
 
## Unit Tests
- All team members must write at least 1 unit tests during the Sprint
- Unit tests must be written for critical non-UI code
- Unit tests must contain sufficient equivalence class and boundary condition test cases
- Unit tests must achieve a 100% pass rate before a PR can be merged

## Code Review
Prior to opening a pull request, the developer must go through the developer review checklist and ensure that all requirements
are met. The completed checklist should be included in the subsequent pull request. All reviewers must then complete the reviewer
checklist prior to approving the pull request.

### Developer Checklist
- [ ] Corresponding User Story/Task on Taiga moved to "Ready For Test"
- [ ] Code builds and runs
- [ ] Code is formatted properly
- [ ] JavaDoc comments are included on essential methods
- [ ] Unclear code is commented
- [ ] Static analysis checks are passing
- [ ] All test cases are passing
- [ ] 100% of critical non-UI code is covered by unit tests
- [ ] Pipeline ran successfully
- [ ] All task requirements are fulfilled
- [ ] **Pulled and merged dev before opening a Pull Request**
- [ ] If the task is the last task to be completed for a story, all story acceptance criteria must be met

### Reviewer Checklist
- [ ] Code builds and runs
- [ ] Code is formatted properly
- [ ] Code is clearly documented and understandable
- [ ] Static analysis checks are passing
- [ ] All test cases are passing
- [ ] All task requirements are fulfilled
- [ ] If the task is the last task to be completed for a story, all story acceptance criteria must be met

## Static Analysis
SpotBugs and Google Java Format will be used to perform static analysis on all new and modified code prior to merging into dev. 
All bugs found by SpotBugs must be reviewed and corrected. If a bug is deemed to be a false positive, and it is decided
not to fix it, this decision and its reasoning must be documented at the relevant point in the code. All formatting issues
reported by Google Java Format must be corrected. 

## Continuous Integration
Continuous Integration will be utilized to run verification tasks on all committed code. Specifically, GitHub Actions will
be configured to run the following tasks:
- Build code
- Run test cases
- Run SpotBugs
- Run Google Java Format

All tasks in the pipeline must complete successfully and have their output reviewed prior to merging a pull request into dev.
