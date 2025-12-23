package domain.participant

class Dealer : Participant() {
    fun getOneVisibleCardInfo(): String {
        return hand.ownCards.firstOrNull()?.toString() ?: "카드 없음"
    }
}
