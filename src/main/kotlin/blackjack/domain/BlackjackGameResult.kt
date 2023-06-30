package blackjack.domain

data class BlackjackGameResult(
    val dealer: DealerResult,
    val players: PlayerResults,
)
