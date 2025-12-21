package model

class Dealer(cards: Cards = Cards()) : Participant(name = "딜러", cards = cards) {
    override fun getPublicCardsOnFirstRound(): Cards {
        return Cards(cards.cards().take(1).toMutableList())
    }
}
