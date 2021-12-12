package blackjack.domain.card

import blackjack.domain.Score

class Cards(cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.map { it.copy() }.toMutableList()
    val cards: List<Card>
        get() = _cards.map { it.copy() }

    val isBust = getScore().isBust

    val isInitial = _cards.size < INITIAL_CARDS_COUNT

    val isBlackjack = getScore().isBlackjack

    fun getScore(): Score {
        var sum = cards
            .filter { it.denomination != Denomination.ACE }
            .sumOf { it.score.score }
            .let { Score(it) }

        repeat(countAce()) {
            sum += sum.getAceScore()
        }
        return sum
    }

    private fun countAce(): Int {
        return cards.count(Card::isAce)
    }

    operator fun plus(other: Card): Cards = Cards(cards + other)

    companion object {
        private const val INITIAL_CARDS_COUNT = 2

        fun from(cardList: List<Card>): Cards {
            return Cards(cardList)
        }
    }
}
