package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardType

const val BLACKJACK_SCORE = 21

class Cards(
    initialValue: List<Card> = emptyList(),
) {
    private val _value: MutableList<Card> =
        MutableList(initialValue.size) { index -> initialValue[index].copy() }

    val value: List<Card>
        get() = _value.toList()

    val size: Int
        get() = _value.size

    fun add(card: Card) = _value.add(card)
    fun sum(): Int =
        if (shouldCountWithAce()) {
            sumWithAce()
        } else {
            _value.sumOf { it.type.score }
        }

    fun takeOutCard(): Card {
        require(_value.isNotEmpty()) { "잘못된 호출입니다. 먼저 카드 목록에 카드를 채우세요." }
        return _value.removeFirst()
    }

    private fun shouldCountWithAce(): Boolean = _value.any { it.type == CardType.ACE }

    private fun sumWithAce(): Int {
        val countOfAce = value.count { it.type == CardType.ACE }
        val cardsSum = filterNotAceCard().sumOf { it.type.score } +
            if (countOfAce > ALLOWED_SOFT_ACE_COUNT) CardType.ACE.score * countOfAce - ALLOWED_SOFT_ACE_COUNT else 0
        return cardsSum.takeIf { BLACKJACK_SCORE - it < CardType.ACE.specialScore }
            ?.let { it + CardType.ACE.score }
            ?: run { cardsSum + CardType.ACE.specialScore }
    }

    private fun filterNotAceCard(): List<Card> = _value.filterNot { it.type == CardType.ACE }

    companion object {
        private const val ALLOWED_SOFT_ACE_COUNT = 1
    }
}
