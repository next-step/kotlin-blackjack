package study.blackjack.model

import study.blackjack.model.Match.Companion.BLACKJACK_NUMBER

/**
 * @author 이상준
 */
data class Cards(
    private val cards: List<Card>,
) {
    fun size(): Int = cards.size

    fun add(card: Card): Cards {
        return Cards(cards + card)
    }

    fun toList(): List<Card> = cards.toList()

    fun calculateScore(): Int {
        val totalScore = cards
            .map { card ->
                if (CardRank.ACE == card.cardRank) {
                    ACE_SOFT_SCORE
                } else {
                    card.score()
                }
            }.sumOf { it }

        return if (totalScore > BLACKJACK_NUMBER) {
            cards.sumOf { it.score() }
        } else {
            totalScore
        }
    }

    companion object {
        private const val ACE_SOFT_SCORE = 11
    }
}
