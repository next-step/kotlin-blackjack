package blackjack.domain.game.result

@JvmInline
value class MatchResults(private val matchResults: List<MatchResult>) : List<MatchResult> by matchResults
