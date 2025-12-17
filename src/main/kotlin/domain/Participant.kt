package domain

abstract class Participant(
    val blackjackCards: BlackjackCards = BlackjackCards(),
) {
    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize() = blackjackCards.cards.size
}
