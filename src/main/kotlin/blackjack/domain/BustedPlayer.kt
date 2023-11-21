package blackjack.domain

class BustedPlayer(
    override val name: String,
    override val cards: Cards,
) : Player
