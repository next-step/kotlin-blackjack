package blackjack.domain

class PreparedPlayer(
    override val name: String,
) : Player {
    override val cards: Cards = Cards(emptyList())
}
