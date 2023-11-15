package blackjack.entity

data class Participant(
    val name: String,
    var cards: Cards,
) {
    val canGetCard: Boolean
        get() {
            val sumOfCards = sumOfCardNumbers()
            return sumOfCards < MAX_OF_SUM_CARDS
        }

    private fun sumOfCardNumbers(): Int = if (cards.cardsContainACard) {
        sumOfCardsWithA()
    } else {
        cards.sumOfCards
    }

    /**
     * A가 가질 수 있는 상태가 1, 11이지만 선택은 참여자가 선택한다고 생각해서 이곳에 함수를 만들었습니다.
     */
    private fun sumOfCardsWithA(): Int = if (cards.sumOfCards + SUPER_A_PLUS_NUMBER > MAX_OF_SUM_CARDS) {
        cards.sumOfCards
    } else {
        cards.sumOfCards + SUPER_A_PLUS_NUMBER
    }

    override fun toString(): String {
        val cardString = cards.joinToString { card -> card.toString() }
        val resultString = "결과: ${sumOfCardNumbers()}"
        return "$name 카드: $cardString - $resultString"
    }

    companion object {
        private const val MAX_OF_SUM_CARDS = 21
        private const val SUPER_A_PLUS_NUMBER = 10
    }
}
