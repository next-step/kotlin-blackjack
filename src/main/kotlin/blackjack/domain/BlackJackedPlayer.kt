package blackjack.domain

class BlackJackedPlayer(
    override val name: String,
    override val cards: Cards,
) : Player
