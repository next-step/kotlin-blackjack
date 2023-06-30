package baclkjack.domain.play

import baclkjack.domain.card.Card

class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()
    fun add(card: Card) {
        _cards.add(card)
    }

    fun score(): Int {

        val sum = _cards.map { it.number }.sumOf { it.value }
        return _cards.filter { it.number.isAce() }.fold(sum) { acc, _ ->
            if (acc > WIN_NUMBER) {
                acc - ACE_VALUE
            } else {
                acc
            }
        }
    }

    fun isBurst(): Boolean = score() > WIN_NUMBER
    fun isBlackJack(): Boolean = score() == WIN_NUMBER

    companion object {
        const val ACE_VALUE = 10
        const val WIN_NUMBER = 21
    }
}
