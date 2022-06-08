fun interface IssueStrategy {
    fun issue(): List<Pair<String, Int>>
}
