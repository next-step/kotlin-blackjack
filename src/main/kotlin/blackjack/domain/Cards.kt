package blackjack.domain

import blackjack.model.CardType

const val BLACKJACK_SCORE = 21

class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value: List<Card>
        get() = _value.toList()

    val size: Int
        get() = _value.size

    fun shuffle() = _value.shuffle()

    fun add(card: Card) = _value.add(card)
    fun sum(): Int =
        if (shouldCountWithAce()) {
            sumWithAce()
        } else {
            _value.sumOf { it.type.score }
        }

    private fun shouldCountWithAce(): Boolean = _value.any { it.type == CardType.ACE }

    private fun sumWithAce(): Int {
        val filteredCardsSum = _value.filterNot { it.type == CardType.ACE }
            .sumOf { it.type.score }
        return filteredCardsSum.takeIf { BLACKJACK_SCORE - it < CardType.ACE.specialScore }
            ?.let { it + CardType.ACE.score }
            ?: run { filteredCardsSum + CardType.ACE.specialScore }
    }
}
