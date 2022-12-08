package blackjack.domain

class Dealer(
    override val cards: Cards
) : Member {
    override val name: String
        get() = DEFAULT_NAME

    override fun ableMoreDrawCard() = resultScore() < DRAW_LIMIT_SCORE
    override fun conditionOfWin(otherMember: Member): Boolean {
        if (this.isEqualNumberThan(otherMember)) {
            return true
        }

        return this.isNearBlackJackThan(otherMember)
    }

    companion object {
        fun init(deck: Deck): Dealer {
            val cards = deck.drawInitAssignCards()
            return Dealer(cards)
        }

        private const val DRAW_LIMIT_SCORE = 17
        private const val DEFAULT_NAME = "딜러"
    }
}
