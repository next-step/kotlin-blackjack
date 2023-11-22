package blackjack_dealer.domain

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState

data class Participant(
    override val name: String,
) : Gamer(name) {

    override fun drawCard(cardDeque: CardDeque) {
        super.drawCard(cardDeque)
        setCurrentState()
    }

    fun changeStateToStand() {
        currentState = GamerCurrentState.STAND
    }

    private fun setCurrentState() {
        val currentScore = gamerCards.getCurrentScore()
        currentState = when (currentScore) {
            in MINIMUM_HIT_NUMBER..MAXIMUM_HIT_NUMBER -> GamerCurrentState.HIT
            BLACK_JACK -> GamerCurrentState.BLACKJACK
            else -> GamerCurrentState.BUST
        }
    }

    companion object {
        private const val MINIMUM_HIT_NUMBER = 1
        private const val MAXIMUM_HIT_NUMBER = 20

        private const val BLACK_JACK = 21

        fun newInstance(name: String, cards: GamerCards): Participant {
            val initialState = findMatchedInitialState(cards)
            return Participant(name = name).apply {
                initializeCard(cards)
                currentState = initialState
            }
        }
        private fun findMatchedInitialState(initialCards: GamerCards): GamerCurrentState {
            val currentScore = initialCards.getCurrentScore()
            return when (currentScore) {
                in MINIMUM_HIT_NUMBER..MAXIMUM_HIT_NUMBER -> GamerCurrentState.HIT
                BLACK_JACK -> GamerCurrentState.BLACKJACK
                else -> GamerCurrentState.BUST
            }
        }
    }
}
