package blackjack.domain

import blackjack.domain.Symbol.Companion.ACE_BONUS_VALUE_THRESHOLD

class Hands(var cards: List<Card>) {
    private lateinit var state: State

    init {
        require(cards.size == INIT_CARD_SIZE) { "시작 카드는 ${INIT_CARD_SIZE}장이어야 합니다." }
        updateState()
    }

    fun sum(): Int {
        var sum = cards.sumOf { it.value }

        if (cards.any { it.symbol == Symbol.ACE } && sum <= ACE_BONUS_VALUE_THRESHOLD) {
            sum += Symbol.ACE_BONUS_VALUE
        }

        return sum
    }

    fun hit(card: Card): State {
        if (isFinished()) {
            error("Finished State에서는 Hit 할 수 없습니다.")
        }

        cards += card
        updateState()

        return state
    }

    fun size(): Int {
        return cards.size
    }

    fun isFinished(): Boolean {
        return state.isFinished
    }

    fun isNotFinished(): Boolean {
        return !state.isFinished
    }

    fun isBust(): Boolean {
        return state == State.BUST
    }

    private fun updateState(): State {
        state = State.from(this)

        return state
    }

    companion object {
        const val INIT_CARD_SIZE = 2
    }
}
