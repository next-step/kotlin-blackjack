package domain.card

import domain.algorithm.ScoreOptimizationAlgorithm

class Cards(
    private val scoreOptimizationAlgorithm: ScoreOptimizationAlgorithm
) {
    private var cards: List<Card> = listOf()

    val count: Int
        get() = this.cards.size

    fun add(card: Card) {
        this.cards += card
    }

    fun score(): Int = scoreOptimizationAlgorithm.optimizeScore(this.cards, TARGET_HIGH_SCORE)

    fun cardList(): List<Card> = this.cards
    fun isAvailableReceiveNumber(availableReceiveNumber: Int): Boolean = this.score() <= availableReceiveNumber

    private companion object {
        const val TARGET_HIGH_SCORE = 21
    }
}
