package blackjack.domain

data class Dealer(
    override val name: Name = NAME,
    override val cards: Cards = Cards()
) : Player {
    constructor(cards: Cards) : this(NAME, cards)

    override fun initialCard(deck: Deck): Player {
        return this.copy(cards = cards + deck.drawInitCards())
    }

    override fun hit(deck: Deck): Player {
        check(canHit()) { "카드를 받을 수 없습니다." }
        return this.copy(cards = cards + deck.draw())
    }

    override fun canHit(): Boolean = cards.countingCard() < DEALER_HIT_RULE_SCORE

    companion object {
        val NAME: Name = Name("딜러")
        const val DEALER_HIT_RULE_SCORE = 17
    }
}
