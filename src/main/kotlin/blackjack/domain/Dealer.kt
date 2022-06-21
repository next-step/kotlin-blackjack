package blackjack.domain

class Dealer : Player(NAME) {

    override fun drawable(): Boolean {
        return getPoints() < STOP_POINT
    }

    override fun addCard(card: Card) {
        check(drawable()) { "Dealer can not draw over $STOP_POINT points" }
        super.addCard(card)
    }

    companion object {
        private const val NAME = "딜러"
        const val STOP_POINT = 17
    }
}
