package domain.player

import domain.algorithm.DefaultScoreOptimizationAlgorithm
import domain.card.Cards

class Player(
    val name: String
) : Playable {

    override val hands: Cards = Cards(scoreOptimizationAlgorithm = DefaultScoreOptimizationAlgorithm)

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

    override fun isAvailableReceive(): Boolean = this.hands.isAvailableReceiveNumber(AVAILABLE_TARGET_NUMBER)

    private companion object {
        const val AVAILABLE_TARGET_NUMBER = 21
    }
}
