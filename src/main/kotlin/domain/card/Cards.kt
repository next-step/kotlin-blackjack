package domain.card

import domain.algorithm.DefaultScoreOptimizationAlgorithm
import domain.algorithm.ScoreOptimizationAlgorithm

class Cards(
    private val scoreOptimizationAlgorithm: ScoreOptimizationAlgorithm = DefaultScoreOptimizationAlgorithm
) {
    private var cards: List<Card> = listOf()

    val count: Int
        get() = this.cards.size

    fun add(card: Card) {
        this.cards += card
    }

    fun score(): Int = scoreOptimizationAlgorithm.optimizeScore(this.cards, TARGET_HIGH_SCORE)

    fun cardList(): List<Card> = this.cards
    fun isAvailableReceiveNumber(limitReceiveScore: Int): Boolean = this.score() < limitReceiveScore

    private companion object {
        const val TARGET_HIGH_SCORE = 21
    }
}
