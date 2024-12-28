package blackjack.domain.player

class Dealer : Participant() {
    fun needsMoreCard(): Boolean {
        return state.hands.score() <= DRAW_THRESHOLD
    }

    fun getState(): DealerState {
        return DealerState(
            isBust = isBust(),
            isBlackjack = score() == 21,
        )
    }

    companion object {
        private const val DRAW_THRESHOLD = 16
    }
}
