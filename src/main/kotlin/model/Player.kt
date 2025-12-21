package model

class Player(name: String, var betAmount: Long = 0) : Participant(name) {
    override fun getPublicCardsOnFirstRound(): Cards = cards
}
