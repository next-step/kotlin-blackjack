package domain

class Dealer(override val cards: Cards = Cards()) : Participant {
    override val name: String = "딜러"

    override fun getPublicCardsOnFirstRound(): Cards {
        return Cards(cards.cards().take(1).toMutableList())
    }
}
