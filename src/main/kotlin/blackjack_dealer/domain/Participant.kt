package blackjack_dealer.domain

import blackjack_dealer.CardGenerator
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.toGamerCards

data class Participant(
    private val name: String,
) {
    private lateinit var gamerCards: GamerCards

    private var currentState: GamerCurrentState = GamerCurrentState.INITIAL

    fun getParticipantName(): String = name
    fun getCurrentCards(): GamerCards = gamerCards
    fun getCurrentGamerState(): GamerCurrentState = currentState
    fun canJoinGame(): Boolean = currentState is GamerCurrentState.HIT

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
            in 1..20 -> GamerCurrentState.HIT
            21 -> GamerCurrentState.BLACKJACK
            else -> GamerCurrentState.BUST
        }
    }

    companion object {
        fun newInstance(name: String, cards: GamerCards): Participant {
            val initialState = initialStateIsBlackJack(cards)
            return Participant(name = name).apply {
                gamerCards = cards
                currentState = initialState
            }
        }

        private fun initialStateIsBlackJack(initialCards: GamerCards): GamerCurrentState =
            if (initialCards.getCurrentScore() == BLACK_JACK) GamerCurrentState.BLACKJACK else GamerCurrentState.HIT

        private const val BLACK_JACK = 21
    }
}
