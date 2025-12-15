package domain

class Dealer(val blackjackCards: BlackjackCards = BlackjackCards()) {
    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize() = blackjackCards.getCards().size

    fun getOneVisibleCardInfo(): String {
        return blackjackCards.getCards().firstOrNull()?.toString() ?: "카드 없음"
    }
}
