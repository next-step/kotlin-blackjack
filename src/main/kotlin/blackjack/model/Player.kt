package blackjack.model

class Player(
    val name: PlayerName,
) {
    private val cards: Cards = Cards()
    val cardSize = cards.size

    companion object {
        fun from(name: String) = Player(PlayerName(name))
    }
}
