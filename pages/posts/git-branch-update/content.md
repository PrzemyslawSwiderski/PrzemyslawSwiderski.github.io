---
title: "Clean update branch in Git"
created: 2025-12-01
readTime: "8 mins"
hidden: false
---

{toc.placeholder}
    
## Updating Your Git Feature Branch Without Merge Commits or Rebases

When working on a feature branch in Git, you often need to incorporate the latest changes from your main branch. While
merge commits and rebases are the standard approaches, sometimes you may want a cleaner alternative 😉.

### The Problem

You're working on a feature branch, and your main branch has moved forward with new commits. You need to update your
feature branch, but:

- You don't want merge commits cluttering your history
- You want to avoid the complexity of interactive rebasing

### The Solution: Reset and Reapply

The simplest approach is to reset your feature branch to the latest main and reapply your changes. Here's how:

#### Step 1: Create a Safety Backup

Always create a backup of your current work before doing any destructive operations:

```bash
git checkout feature-branch
git branch feature-branch-backup
```

#### Step 2: Fetch the Latest Changes

Get the latest commits from the remote repository:

```bash
git fetch origin
```

#### Step 3: Reset Your Branch

Reset your feature branch to match the target branch (usually `main` or `develop`):

```bash
git reset --hard origin/main
```

This moves your branch pointer to the latest commit on `main`, effectively "updating" your base.

#### Step 4: Reapply Your Changes

Now cherry-pick your original commits back onto the updated branch:

```bash
git cherry-pick commit1 commit2 commit3
```

Or if you know the commit range:

```bash
git cherry-pick base-commit..feature-branch-backup
```

### Even Simpler: The Squash Approach

If you haven't pushed your feature branch yet and don't mind squashing all your work into a single commit, this is even
easier:

```bash
git fetch origin
git reset --soft origin/main
git commit -m "Add feature: your feature description"
```

This approach:

1. Moves your branch pointer to the latest main
2. Keeps all your changes staged (thanks to `--soft`)
3. Lets you create one clean commit with all your work

### When to Use This Approach

This method works best when:

- You want a linear history without merge commits
- You're comfortable creating new commit SHAs
- You haven't shared your branch with others (or can force-push)
- You want a cleaner alternative to interactive rebase

### Important Notes

- If you've already pushed your feature branch, you'll need to force-push:
  `git push --force-with-lease origin feature-branch`
- The `--force-with-lease` flag is safer than `--force` as it won't overwrite work if someone else has pushed to your
  branch
- Always coordinate with your team before force-pushing to shared branches

### Cleanup

Once you've verified everything works, you can delete your backup branch:

```bash
git branch -D feature-branch-backup
```

---

## Conclusion

This approach gives you a clean, linear history without the complexity of rebasing or the clutter of merge commits. It's
particularly useful for personal feature branches where you want to maintain a tidy commit history.