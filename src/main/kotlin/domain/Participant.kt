package domain

abstract class Participant(
    protected val blackjackCards: BlackjackCards = BlackjackCards()
) {
    abstract fun openFirstRound(): List<Card>

    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize(): Int = blackjackCards.getCards().size

    fun displayCardInfo(): String = blackjackCards.displayCardInfo()

    fun calculateScore(): Int = blackjackCards.calculateScore()
}