package blackjack.domain

import blackjack.domain.Const.BLACKJACK_NUMBER
import kotlin.math.abs

private const val ACE_VALUE_1 = 1
private const val ACE_VALUE_2 = 11

class Cards(
    items: Set<Card>
) {
    constructor(vararg cards: Card) : this(cards.toMutableSet())

    private val _items: MutableSet<Card> = items.toMutableSet()
    val elements: Set<Card>
        get() = _items.toSet()
    val size: Int
        get() = _items.size

    init {
        require(_items.size >= MIN_SIZE) { "카드는 최소 2장 이상이어야 합니다." }
    }

    fun add(card: Card) {
        require(!_items.contains(card)) { "중복된 카드 ${card}는 추가할 수 없어요." }
        _items.add(card)
    }

    fun score(): Int {
        val allSum = _items.sumOf { card -> card.sumAllScore() }
        val aceCount = _items.count { card -> card.isAce() }

        if (aceCount == 0) {
            return allSum
        }

        return (0 until aceCount).fold(allSum) { acc, _ -> minusAceNumberCaseOne(acc) }
    }

    fun blackJack(): Boolean {
        val firstCards = _items.toList().subList(0, MIN_SIZE)

        if (!firstCards.any { it.isAce() }) {
            return false
        }

        return firstCards.any { it.isTenNumberCard() }
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
