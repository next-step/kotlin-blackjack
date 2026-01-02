package domain

interface Participant {
    val name: String
    val cards: Cards

    fun getPublicCardsOnFirstRound(): Cards

    fun isBust(): Boolean = cards.isBust()

    fun getScore(): Int = cards.calculateScore()
}
