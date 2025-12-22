package domain

interface Participant {
    val name: String
    var money: Int
    val cards: Cards

    fun getPublicCardsOnFirstRound(): Cards

    fun isDrawAvailable(): Boolean

    fun isBust(): Boolean = cards.isBust()

    fun isBlackJack(): Boolean = cards.isBlackJack()

    fun getScore(): Int = cards.calculateScore()
}
