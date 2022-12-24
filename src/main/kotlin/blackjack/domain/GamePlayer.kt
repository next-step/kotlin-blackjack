package blackjack.domain

import blackjack.model.Card

class GamePlayer(
    override val name: String,
    initialState: State = Started()
) : Player {
    init {
        require(name.isNotBlank()) { "Player 이름은 공백이 될 수 없습니다. 유효한 Player 명을 입력해주세요." }
    }

    private var _state: State = initialState
    override val cards: Cards
        get() = _state.cards
    override val state: State
        get() = _state

    override val finished: Boolean
        get() = state.finished

    override fun shouldBeReadyToPlay(): Boolean =
        cards.size == Game.INITIAL_CARDS_COUNT && state !is Started

    override fun draw(card: Card) {
        _state = state.draw(card)
    }

    override fun stay() {
        _state = state.stay()
    }

    override fun sumCards(): Int = state.cards.sum()
}
