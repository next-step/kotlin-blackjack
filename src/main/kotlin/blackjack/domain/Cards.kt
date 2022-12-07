package blackjack.domain

import blackjack.domain.Const.BLACKJACK_NUMBER
import kotlin.math.abs

private const val ACE_VALUE_1 = 1
private const val ACE_VALUE_2 = 11

class Cards(
    private val items: MutableSet<Card>
) {
    constructor(vararg cards: Card) : this(cards.toMutableSet())

    val size: Int
        get() = items.size
    val elements: Set<Card>
        get() = items

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

        return (0 until aceCount).fold(allSum) { acc, _ -> minusAceNumberCaseOne(acc) }
    }

    private fun minusAceNumberCaseOne(acc: Int): Int {
        val candidate1 = acc - ACE_VALUE_1
        val candidate2 = acc - ACE_VALUE_2

        return if (candidate1.isNearBlackJackThan(candidate2)) {
            candidate1
        } else {
            candidate2
        }
    }
    private fun Int.isNearBlackJackThan(candidate2: Int) =
        abs(this - BLACKJACK_NUMBER) < abs(candidate2 - BLACKJACK_NUMBER)

    companion object {
        private const val MIN_SIZE = 2
    }
}
