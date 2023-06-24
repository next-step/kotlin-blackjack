package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card

data class PlayerCards(val cards: MutableList<Card>) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun isBlackJack(): Boolean = size() == BLACKJACK_CARDS_CNT && isFinished()

    fun isFinished(): Boolean = minSumCardsPoint() == FINISH_POINT || maxSumCardsPoint() == FINISH_POINT

    private fun minSumCardsPoint(): Int = cards.sumOf { it.minPoint() }

    private fun maxSumCardsPoint(): Int = cards.sumOf { it.maxPoint() }

    fun isBurst(): Boolean {
        return minSumCardsPoint() > FINISH_POINT
    }

    fun point(): Int = when {
        isFinished() -> FINISH_POINT
        maxSumCardsPoint() < FINISH_POINT -> maxSumCardsPoint()
        else -> minSumCardsPoint()
    }

    fun size() = cards.size

    fun descs(): Set<String> = cards.map { it.desc() }.toSet()

    companion object {
        private const val FINISH_POINT = 21
        private const val BLACKJACK_CARDS_CNT = 2
        fun of(cards: List<Card>): PlayerCards = PlayerCards(cards.toMutableList())
    }
}
