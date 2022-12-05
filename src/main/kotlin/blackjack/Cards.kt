package blackjack

import kotlin.math.abs

private const val BLACKJACK_NUMBER = 21

private const val ACE_VALUE_1 = 1
private const val ACE_VALUE_2 = 11

class Cards(
    val items: MutableSet<Card>
) {
    val size: Int
        get() = items.size

    init {
        require(items.size >= MIN_SIZE) { "카드는 최소 2장 이상이어야 합니다." }
    }

    fun add(card: Card) {
        require(!items.contains(card)) { "중복된 카드 ${card}는 추가할 수 없어요." }
        items.add(card)
    }

    fun score(): Int {
        val allSum = items.sumOf { card -> card.sumAllScore() }
        val aceCount = items.count { card -> card.number == Number.ACE }

        if (aceCount == 0) {
            return allSum
        }

        return (0 until aceCount).fold(allSum) { acc, _ ->
            val candidate1 = acc - ACE_VALUE_1
            val candidate2 = acc - ACE_VALUE_2

            if (candidate1.isNearBlackJackThan(candidate2)) {
                candidate1
            } else {
                candidate2
            }
        }
    }

    private fun Int.isNearBlackJackThan(candidate2: Int) =
        abs(this - BLACKJACK_NUMBER) < abs(candidate2 - BLACKJACK_NUMBER)

    companion object {
        private const val MIN_SIZE = 2
    }
}
