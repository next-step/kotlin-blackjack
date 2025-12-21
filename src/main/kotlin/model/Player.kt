package model

class Player(name: String, cards: Cards = Cards(), var betAmount: Long = 0) : Participant(name, cards) {
    override fun getPublicCardsOnFirstRound(): Cards = cards
}
