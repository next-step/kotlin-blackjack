package domain

interface Participant {
    val name: String
    val betMoney: Int
    val cards: Cards

    fun getPublicCardsOnFirstRound(): Cards

    fun isDrawAvailable(): Boolean
}
