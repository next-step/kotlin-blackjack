package blackjack.domain.player

data class ReceiveCardStatistics(
    val playerName: String,
    val cardNames: List<String>,
    val cardSum: Int,
)
