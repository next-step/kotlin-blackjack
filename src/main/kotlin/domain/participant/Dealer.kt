package domain.participant

class Dealer : Participant() {
    fun getOneVisibleCardInfo(): String {
        return participantHand.ownCards.firstOrNull()?.toString() ?: "카드 없음"
    }
}
