package blackjack.domain

class Challenger(
    override val name: String,
    override val cards: Cards = Cards.empty(),
    state: State? = null
) : Player {

    override val state: State = state ?: getStateFrom(cards)

    init {
        validateStateWithScore()
    }

    private fun validateStateWithScore() {
        if (getScore() > Cards.BLACK_JACK_SCORE && state !is State.Busted) {
            throw IllegalArgumentException("총점이 21점을 넘으면 상태 값이 Busted 이어야 합니다. 총점 : ${getScore()}입력 : $state")
        }
    }

    override fun getStateFrom(cards: Cards): State = when {
        cards.sumScores() > Cards.BLACK_JACK_SCORE -> State.Busted
        cards.isBlackJack() -> State.BlackJack
        else -> State.Playing
    }

    override fun copy(name: String, cards: Cards, state: State): Player {
        return Challenger(name, cards, state)
    }
}
