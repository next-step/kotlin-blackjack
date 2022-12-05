package blackjack

import kotlin.math.abs

private const val BLACKJACK_NUMBER = 21

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
            if (abs(acc - 1 - BLACKJACK_NUMBER) < abs(acc - 11 - BLACKJACK_NUMBER)) {
                acc - 1
            } else {
                acc - 11
            }
        }
    }

    companion object {
        private const val MIN_SIZE = 2
    }
}
