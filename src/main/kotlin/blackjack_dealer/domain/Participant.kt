package blackjack_dealer.domain

import blackjack_dealer.CardGenerator
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.toGamerCards

data class Participant(
    override val name: String,
) : Gamer(name) {
    fun canJoinGame(): Boolean = currentState is GamerCurrentState.HIT
    fun getCurrentGamerState(): GamerCurrentState = currentState

    fun drawCard(cardDeque: CardDeque) {
        gamerCards = gamerCards.trumpCard.plus(CardGenerator.generateSingleCard(cardDeque)).toGamerCards()
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
        fun newInstance(name: String, cards: GamerCards): Participant {
            val initialState = initialStateIsBlackJack(cards)
            return Participant(name = name).apply {
                gamerCards = cards
                currentState = initialState
            }
        }

        private fun initialStateIsBlackJack(initialCards: GamerCards): GamerCurrentState =
            if (initialCards.getCurrentScore() == BLACK_JACK) GamerCurrentState.BLACKJACK else GamerCurrentState.HIT
    }
}
