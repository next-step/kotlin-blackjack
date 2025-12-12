package domain

interface Participant {
    val name: String
    val cards: Cards

    fun getPublicCardsOnFirstRound(): Cards
}
