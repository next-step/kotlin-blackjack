package blackjack.model.card

import blackjack.model.Config

sealed interface State {
    val finalScore: Int

    data class Bust(override val finalScore: Int) : State
    data class BlackJack(override val finalScore: Int = Config.BLACK_JACK_SCORE) : State
    data class Running(override val finalScore: Int) : State

    companion object {

        fun of(cardList: List<Card>): State {
            return Scores.of(Cards(cardList)).state
        }
    }
}
