package blackjack

class Dealer(initialCards: List<Card>) : Participant(DEALER_NAME, initialCards) {
    override fun receive(newCard: Card) {
        check(shouldDrawCard()) {
            "현재 딜러는 히트할 수 없는 상태입니다: sumOfHand=${sumOfHand()}"
        }
        super.receive(newCard)
    }

    fun shouldDrawCard(): Boolean = sumOfHand() <= DEALER_HIT_THRESHOLD

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
