package blackjack_dealer.domain

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState

data class Dealer(
    override val name: String = DEALER_NAME
) : Gamer(name) {
    override fun drawCard(cardDeque: CardDeque) {
        super.drawCard(cardDeque)
        currentState = findDealerMatchedState(gamerCards)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val MINIMUM_HIT_NUMBER = 4
        private const val MAXIMUM_HIT_NUMBER = 16
        private const val MINIMUM_STAND_NUMBER = 17
        private const val MAXIMUM_STAND_NUMBER = 20

        fun newInstance(cards: GamerCards): Dealer {
            val initialState = findDealerMatchedState(cards)
            return Dealer().apply {
                initializeCard(cards)
                currentState = initialState
            }
        }

        private fun findDealerMatchedState(cards: GamerCards): GamerCurrentState {
            val cardScores = cards.getCurrentScore()
            return when (cardScores) {
                in MINIMUM_HIT_NUMBER..MAXIMUM_HIT_NUMBER -> GamerCurrentState.HIT
                in MINIMUM_STAND_NUMBER..MAXIMUM_STAND_NUMBER -> GamerCurrentState.STAND
                BLACK_JACK -> GamerCurrentState.INITIAL_BLACKJACK
                else -> GamerCurrentState.BUST
            }
        }
    }
}
