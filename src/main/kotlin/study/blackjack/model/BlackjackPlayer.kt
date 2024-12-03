package study.blackjack.model

import study.blackjack.BlackjackGameService.Companion.BLACKJACK

/**
 * @author 이상준
 */
class BlackjackPlayer(
    val name: String,
    cards: MutableList<Card> = mutableListOf(),
) {
    var cards: MutableList<Card> = cards
        private set

    fun addCard(card: Card) {
        cards.add(card)
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
