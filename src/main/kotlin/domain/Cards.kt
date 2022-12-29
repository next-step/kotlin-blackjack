package domain

import domain.algorithm.ScoreOptimizationAlgorithm

class Cards(
    private val scoreOptimizationAlgorithm: ScoreOptimizationAlgorithm
) {
    private var cards: List<Card> = listOf()

    val count: Int
        get() = this.cards.size

    fun add(card: Card) {
        val mutableList = this.cards.toMutableList()
        mutableList.add(card)
        this.cards = mutableList.toList()
    }

    fun score(): Int = scoreOptimizationAlgorithm.optimizeScore(this.cards, TARGET_HIGH_SCORE)

    fun cardList(): List<Card> = this.cards
    fun isAvailableReceiveNumber(): Boolean = this.score() <= TARGET_HIGH_SCORE

    private companion object {
        const val TARGET_HIGH_SCORE = 21
    }
}
