package blackjack.domain.game

data class MatchResult(
    val dealerMatchResult: DealerMatchResult,
    val playerMatchResults: List<PlayerMatchResult>,
)
