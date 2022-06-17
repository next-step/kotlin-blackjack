package blackjack.model

class Player private constructor(
    val name: PlayerName,
    val cards: Cards = Cards()
) {
    val cardSize
        get() = cards.size

    fun receiveCard(card: Card) = cards.addOne(card)

    companion object {
        fun from(name: String) = Player(PlayerName(name))
    }
}
