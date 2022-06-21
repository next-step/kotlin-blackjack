package domain

open class Player(val name: String) {
    private val holdingCards = mutableListOf<Card>()
    val cards
        get() = holdingCards.toList()
    var match: Match = Match.LOSE
    var battingAmount: Int = 0
    var revenue: Int = 0

    fun offer(cards: List<Card>) {
        if (cards.isNotEmpty()) {
            require(cards.all { !holdingCards.contains(it) }) { SAME_CARD_MESSAGE }
            holdingCards += cards
        }
    }

    fun getSumOfCards(): Int {
        val sum = holdingCards.sumOf { it.denomination.point }

        val aceContains = holdingCards.any { it.denomination == Card.CardDenomination.ACE }
        if(aceContains && sum == BUST_ACE_THRESHOLD_SCORE) {
            return BUST_THRESHOLD_SCORE
        }

        return sum
    }

    companion object {
        private const val SAME_CARD_MESSAGE = "패에 있는 것과 동일한 카드가 주어졌습니다"
        private const val BUST_THRESHOLD_SCORE = 21
        private const val BUST_ACE_THRESHOLD_SCORE = 11
    }
}
