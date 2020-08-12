package blackjack.domain

abstract class Player(
    val name: String,
    val cards: Cards = Cards.empty(),
    val state: State = getStateFrom(cards)
) {
    init {
        validateStateWithScore()
    }

    private fun validateStateWithScore() {
        if (getScore() > Cards.BLACK_JACK_SCORE && state !is State.Busted) {
            throw IllegalArgumentException("총점이 21점을 넘으면 상태 값이 Busted 이어야 합니다. 총점 : ${getScore()}입력 : $state")
        }
    }

    fun getScore(): Int {
        return cards.sumScores()
    }

    fun stand(): Player {
        return this.copy(state = State.Stand)
    }

    fun deal(deck: DrawStrategy): Player {
        return this.copy(cards = this.cards + deck.getDealCards())
    }

    abstract fun hit(deck: DrawStrategy): Player

    abstract fun copy(
        name: String = this.name,
        cards: Cards = this.cards,
        state: State = this.state
    ): Player

    companion object {
        @JvmStatic
        protected fun getStateFrom(cards: Cards): State {
            if (cards.sumScores() > Cards.BLACK_JACK_SCORE) {
                return State.Busted
            }
            return State.Playing
        }
    }
}

// getStateFrom 을 class 내부로 옮겨서  abstract로 하려니 nonFinal Method Calling 경고가 뜨네..
// 이놈을 오버라이드 하는게 맞다고생각하는데 어렵네...
