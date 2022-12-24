package domain

import domain.algorithm.DefaultScoreOptimizationAlgorithm

class Player(
    val name: String
) {

    val hands: Cards = Cards(scoreOptimizationAlgorithm = DefaultScoreOptimizationAlgorithm)

    val handsCardCount: Int
        get() = hands.count

    fun receiveCard(card: Card) {
        this.hands.add(card)
    }

    fun handsCardScore(): Int = this.hands.score()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun isAvailableReceive(): Boolean = this.hands.isExceedsAvailableReceiveNumber()
}
