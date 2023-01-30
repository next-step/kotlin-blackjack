package blackjack.domain

class Dealer(override val ownCards: OwnCards = OwnCards(Draw.FIRST_DRAW_COUNT)) : Person(name = "딜러") {

    fun changeDrawable() {
        super.changeDrawable((ownCards.sumCardNumber() < LEAST_CARD_SUM))
    }

    companion object {
        const val LEAST_CARD_SUM = 16
    }
}
