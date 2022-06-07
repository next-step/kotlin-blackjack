./gradlew ktlintApplyToIdea
mkdir .git/hooks
./gradlew addKtlintCheckGitPreCommitHook
./gradlew clean check