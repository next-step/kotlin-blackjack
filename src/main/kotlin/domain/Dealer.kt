package domain

class Dealer : Participant() {
    fun getOneVisibleCardInfo(): String {
        return blackjackCards.cards.firstOrNull()?.toString() ?: "카드 없음"
    }
}
