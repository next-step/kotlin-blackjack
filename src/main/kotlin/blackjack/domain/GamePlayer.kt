package blackjack.domain

data class GamePlayer(
    override val name: Name,
    override val cards: Cards = Cards.empty(),
) : Player {

    constructor(name: String) : this(Name(name))

    override fun initialCard(deck: Deck): Player {
        return this.copy(cards = cards + deck.drawInitCards())
    }

    override fun hit(deck: Deck): Player {
        check(canHit()) { "카드를 받을 수 없습니다." }
        return this.copy(cards = cards + deck.draw())
    }
}
