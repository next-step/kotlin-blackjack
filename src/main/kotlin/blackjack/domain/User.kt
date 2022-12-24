package blackjack.domain

class User(
    override val name: Name,
    override val cards: Cards = Cards()
): Player {
    constructor(name: String): this(name = Name(name))

    override fun copy(name: Name, cards: Cards): Player {
        return User(name = name, cards = cards)
    }
}