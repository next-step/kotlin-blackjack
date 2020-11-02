package blackjack.domain

import blackjack.domain.Game.Companion.MAXIMUM_GAME_SCORE

class Cards(cards: Set<Card>) {

    private val cards: MutableSet<Card> = cards.toMutableSet()

    private fun getCards() = cards

    fun size() = cards.size

    fun getTotalScore(): Int {
        return cards.map { it.score() }.sum()
    }

    fun addCard(card: Card): Set<Card> {
        cards.add(card)
        return getCards()
    }

    override fun toString(): String {
        return cards.map { it }.toString()
    }

    fun isMoreThanMaxScore(number: Int): Boolean {
        return number >= MAXIMUM_GAME_SCORE
    }
}
