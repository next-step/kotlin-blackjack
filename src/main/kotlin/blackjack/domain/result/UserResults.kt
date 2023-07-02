package blackjack.domain.result

data class UserResults(private val userResults: List<UserResult>) : Iterable<UserResult> by userResults
