package blackjack.domain.participant

import blackjack.domain.card.Card

sealed class Participant(val name: String, val state: State) {

    abstract val continueDrawing: Boolean

    init {
        require(name.isNotBlank()) { NAME_EXCEPTION }
    }

    fun drawInitCards(cards: List<Card>) {
        cards.forEach { state + it }
    }

    fun drawCard(card: Card) {
        state + card
    }

    companion object {
        private const val NAME_EXCEPTION = "이름을 정확하게 입력해주십시오"
    }
}
