package blackjack.domain

class Dealer(override val cards: Cards = Cards()) : Player {
    override val name: Name = NAME
    override fun copy(name: Name, cards: Cards): Player {
        return Dealer(cards = cards)
    }

    override fun canHit(): Boolean = cards.countingCard() < DEALER_HIT_RULE_SCORE

    companion object {
        val NAME: Name = Name("딜러")
        const val DEALER_HIT_RULE_SCORE = 17
    }
}
