package blackjack.domain.card

import blackjack.domain.model.BlackJackErrorCode

abstract class PlayingCards(private val cards: MutableSet<Card> = mutableSetOf()) : Set<Card> by cards {

    fun add(card: Card) {
        check(value = card !in cards) {
            BlackJackErrorCode.CAN_NOT_ADD_DUPLICATE_CARD.message(
                arrayOf(card)
            )
        }

        cards.add(element = card)
    }

    fun isBust(): Boolean = cards.sumOf { it.denomination.getMinimumScore() } > BUST_LIMIT

    fun calculateOptimalScore(): Int = cards.fold(initial = listOf(element = ZERO)) { acc, card ->
        sumScores(sumScores = acc, card = card)
    }.minBy { nearScore(score = it) }

    private fun sumScores(sumScores: List<Int>, card: Card) = card.denomination.scores
        .flatMap { score -> sumScore(sumScores = sumScores, score = score) }

    private fun sumScore(sumScores: List<Int>, score: Int) = sumScores.toList().map { it + score }

    private fun nearScore(score: Int) = when {
        score > BUST_LIMIT -> Int.MAX_VALUE
        else -> BUST_LIMIT - score
    }

    companion object {
        private const val ZERO: Int = 0
        private const val BUST_LIMIT: Int = 21
    }
}
