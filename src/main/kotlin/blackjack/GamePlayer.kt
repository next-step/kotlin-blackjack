package blackjack

data class GamePlayer(
    val name: String,
    val cards: List<Card> = emptyList()
)
