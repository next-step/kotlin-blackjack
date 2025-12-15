package domain

class Dealer(val blackjackCards: BlackjackCards = BlackjackCards()) {
    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize() = blackjackCards.cards.size

    fun getOneVisibleCardInfo(): String {
        return blackjackCards.cards.firstOrNull()?.toString() ?: "카드 없음"
    }
}
