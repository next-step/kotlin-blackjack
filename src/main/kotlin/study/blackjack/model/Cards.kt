package study.blackjack.model

import study.blackjack.model.Match.Companion.BLACKJACK

/**
 * @author 이상준
 */
data class Cards(
    private var cards: MutableList<Card> = mutableListOf(),
) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun addAllCards(cards: Cards) {
        cards.cards.forEach {
            addCard(it)
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun cardCount(): Int {
        return cards.size
    }

    fun calculateScore(): Int {
        val totalScore = cards.sumOf { it.score() }

        return if (totalScore > BLACKJACK) {
            cards.sumOf { it.score(false) }
        } else {
            totalScore
        }
    }
}
