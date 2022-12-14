package blackjack.domain

class GamePlayer(
    override val name: Name,
    override val cards: Cards = Cards.empty(),
) : Player {

    constructor(name: String) : this(Name(name))

    override fun copy(name: Name, cards: Cards): Player {
        return GamePlayer(name = name, cards = cards)
    }
}
