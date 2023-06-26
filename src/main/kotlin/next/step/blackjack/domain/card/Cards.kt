package next.step.blackjack.domain.card

import next.step.blackjack.domain.game.GameResult
import next.step.blackjack.util.CombinationUtils

data class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun isBlackjack(): Boolean = size() == BLACKJACK_CARDS_CNT && isFinished()

    fun isFinished(): Boolean = possiblePoints().contains(FINISH_POINT)

    fun isBurst(): Boolean {
        return minSumCardsPoint() > FINISH_POINT
    }

    private fun minSumCardsPoint(): Int = cards.sumOf { it.minPoint() }

    fun point(): Int {
        return when {
            isFinished() -> FINISH_POINT
            isBurst() -> minSumCardsPoint()
            else -> possiblePoints().filter { it < FINISH_POINT }.max()
        }
    }

    private fun possiblePoints(): Set<Int> = CombinationUtils.possiblePoints(cards)

    fun size(): Int = cards.size

    fun descs(): Set<String> = cards.map { it.desc() }.toSet()

    fun descFirst(): String {
        require(cards.isNotEmpty()) { "카드 개수가 1개 이상이어야 조회 가능합니다." }
        return cards.first().desc()
    }

    fun fight(other: Cards): GameResult = when {
        point() > other.point() -> GameResult.WIN
        point() < other.point() -> GameResult.LOSE
        else -> GameResult.TIE
    }

    companion object {
        private const val BLACKJACK_CARDS_CNT = 2

        private const val FINISH_POINT = 21
        fun of(cards: List<Card>): Cards = Cards(cards.toMutableList())
    }
}
