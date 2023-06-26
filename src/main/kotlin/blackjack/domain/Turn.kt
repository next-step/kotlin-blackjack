package blackjack.domain

@JvmInline
value class Turn(
    val value: Int = DEALING_TURN
) {
    fun isDealingTurn() = value == DEALING_TURN

    fun nextTurn() = Turn(value + 1)

    fun isSameTurn(turn: Int) = value == turn

    fun isHigherTurn(turn: Int)= value < turn

    companion object {
        private const val DEALING_TURN = -1
    }
}
