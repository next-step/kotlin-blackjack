package blackjack.domain

class OnGoingPlayer(
    override val name: String,
    override val cards: Cards,
) : Player
