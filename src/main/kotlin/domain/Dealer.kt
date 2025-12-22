package domain

const val DEALER_STAND_SCORE = 17

class Dealer(override val cards: Cards = Cards()) : Participant {
    override val name: String = "딜러"
    override val betMoney: Int = 0

    override fun getPublicCardsOnFirstRound(): Cards {
        return Cards(cards.cards().take(1).toMutableList())
    }

    override fun isDrawAvailable(): Boolean = cards.calculateScore() < DEALER_STAND_SCORE
}
