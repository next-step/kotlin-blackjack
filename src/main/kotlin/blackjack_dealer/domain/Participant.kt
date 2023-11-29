package blackjack_dealer.domain

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.result.ParticipantResult
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.state.ParticipantResultState

data class Participant(
    override val name: String,
    private var betAmount: Int,
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

    fun createParticipantResult(dealer: Dealer): ParticipantResult {
        val participantState = createParticipantResultState(dealer)
        return ParticipantResult(name = getGamerName(), resultState = participantState)
    }

    private fun createParticipantResultState(dealer: Dealer): ParticipantResultState {
        val dealerScore = dealer.getCurrentCards().getCurrentScore()
        val participantScore = getCurrentCards().getCurrentScore()

        if (gamerIsBust()) return ParticipantResultState.LOSE
        if (participantScore == BLACK_JACK) return ParticipantResultState.BLACKJACK
        if (dealerScore > BLACK_JACK) return ParticipantResultState.WIN

        return when {
            dealerScore - participantScore > 0 -> ParticipantResultState.LOSE
            dealerScore - participantScore < 0 -> ParticipantResultState.WIN
            else -> ParticipantResultState.DRAW
        }
    }

    fun getCurrentBetAmount(): Int {
        return betAmount
    }

    fun getResultBetAmount(dealer: Dealer): Int {
        val participantState = createParticipantResultState(dealer)
        return createBetAmountWithResultState(participantState, dealer)
    }

    private fun createBetAmountWithResultState(
        participantState: ParticipantResultState,
        dealer: Dealer
    ): Int = when (participantState) {
        ParticipantResultState.WIN -> {
            betAmount
        }

        ParticipantResultState.LOSE -> {
            -betAmount
        }

        ParticipantResultState.DRAW -> {
            0
        }

        ParticipantResultState.BLACKJACK -> {
            betAmountWhenStateIsBlackJack(dealer)
        }
    }

    private fun betAmountWhenStateIsBlackJack(dealer: Dealer): Int =
        if (dealer.getCurrentGamerState() == GamerCurrentState.BLACKJACK) {
            0
        } else {
            (betAmount * 1.5).toInt()
        }

    companion object {
        private const val MINIMUM_HIT_NUMBER = 1
        private const val MAXIMUM_HIT_NUMBER = 20

        private const val BLACK_JACK = 21

        fun newInstance(name: String, cards: GamerCards, betAmount: Int = 0): Participant {
            val initialState = findMatchedInitialState(cards)
            return Participant(name = name, betAmount = betAmount).apply {
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
