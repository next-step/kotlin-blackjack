package blackjack.domain

class GameUser(val name: String) {
    var doneGame = false
        get() = field || points >= BLACKJACK_POINT
    val cards = mutableListOf<BlackJackCard>()
    val points: Int
        get() {
            return calculatePoints()
        }

    fun addCard(card: BlackJackCard) {
        cards.add(card)
        println(points)
    }

    private fun calculatePoints(): Int {
        val sumPoint =
            cards.sumOf {
                it.getPoint()
            }
        val aceCount =
            cards.filter {
                it.isAceCard()
            }.size
        return consumeAceCard(sumPoint, aceCount)
    }

    private fun consumeAceCard(
        sumPoint: Int,
        aceCount: Int,
    ): Int {
        return sumPoint +
            if (aceCount > 0 && sumPoint <= ACE_CARD_THRESHOLD) {
                ACE_CARD_EXTRA_POINT
            } else {
                0
            }
    }

    companion object {
        private const val BLACKJACK_POINT = 21
        private const val ACE_CARD_THRESHOLD = 11
        private const val ACE_CARD_EXTRA_POINT = 10
    }
}
