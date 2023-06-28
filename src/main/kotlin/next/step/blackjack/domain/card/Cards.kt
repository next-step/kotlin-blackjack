package next.step.blackjack.domain.card

import next.step.blackjack.domain.game.GameResult
import next.step.blackjack.util.CombinationUtils

data class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun cards(): List<Card> = cards.toList()

    fun size(): Int = cards.size

    fun descs(): Set<String> = cards.map { it.desc() }.toSet()

    fun descFirst(): String {
        require(cards.isNotEmpty()) { "카드 개수가 1개 이상이어야 조회 가능합니다." }
        return cards.first().desc()
    }

    fun point(): Int = when {
        isHit() -> HIT_POINT
        isBurst() -> minSumCardsPoint()
        else -> possiblePoints().filter { it < HIT_POINT }.max()
    }

    private fun minSumCardsPoint(): Int = cards.sumOf { it.minPoint() }

    private fun possiblePoints(): Set<Int> = CombinationUtils.possiblePoints(cards)

    fun isBlackjack(): Boolean = size() == BLACKJACK_CARDS_CNT && isHit()

    fun isHit(): Boolean = possiblePoints().contains(HIT_POINT)

    fun isBurst(): Boolean = minSumCardsPoint() > HIT_POINT

    fun fight(other: Cards): GameResult = when {
        point() > other.point() -> GameResult.WIN
        point() < other.point() -> GameResult.LOSE
        else -> GameResult.TIE
    }

    companion object {
        private const val BLACKJACK_CARDS_CNT = 2

        private const val HIT_POINT = 21

        fun of(cards: List<Card>): Cards = Cards(cards.toMutableList())
    }
}
