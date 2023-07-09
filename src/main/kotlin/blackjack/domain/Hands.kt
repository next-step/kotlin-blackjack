package blackjack.domain

class Hands(var cards: List<Card>) {
    lateinit var state: State

    init {
        require(cards.size == INIT_CARD_SIZE) { "시작 카드는 ${INIT_CARD_SIZE}장이어야 합니다." }
        updateState()
    }

    fun sum(): Int {
        return cards.sumOf { it.value }
    }

    fun hit(card: Card): State {
        if (isFinished()) {
            throw IllegalStateException("Finished State에서는 Hit 할 수 없습니다.")
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

    private fun updateState(): State {
        state = State.from(this)

        return state
    }

    companion object {
        const val INIT_CARD_SIZE = 2
    }
}
