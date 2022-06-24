package domain

open class Player(val name: String) {
    private val holdingCards = mutableListOf<Card>()
    val cards
        get() = holdingCards.toList()
    var match: Match = Match.LOSE

    fun offer(cards: List<Card>) {
        if (cards.isNotEmpty()) {
            require(cards.all { !holdingCards.contains(it) }) { SAME_CARD_MESSAGE }
            holdingCards += cards
        }
    }

    fun getSumOfCards(): Int {
        return holdingCards.sumOf { it.denomination.point }
    }

    companion object {
        private const val SAME_CARD_MESSAGE = "패에 있는 것과 동일한 카드가 주어졌습니다"
    }
}
