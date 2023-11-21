package blackjack_dealer.domain

import blackjack_dealer.CardGenerator
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.toGamerCards

data class Dealer(
    private val name: String = DEALER_NAME
) {
    private lateinit var gamerCards: GamerCards
    private var currentState: GamerCurrentState = GamerCurrentState.INITIAL

    fun getDealerName(): String = name
    fun getCurrentCards(): GamerCards = gamerCards
    fun canKeepPlayingGame(): Boolean = currentState is GamerCurrentState.HIT

    fun getOneMoreCardIfHit(cardDeque: CardDeque) {
        gamerCards = gamerCards.plus(listOf(CardGenerator.generateSingleCard(cardDeque))).toGamerCards()
        currentState = findDealerMatchedState(gamerCards)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        fun newInstance(cards: GamerCards): Dealer {
            val initialState = findDealerMatchedState(cards)
            return Dealer().apply {
                gamerCards = cards
                currentState = initialState
            }
        }

        private fun findDealerMatchedState(cards: GamerCards): GamerCurrentState {
            val cardScores = cards.getCurrentScore()
            return when (cardScores) {
                in 4..16 -> GamerCurrentState.HIT
                in 17..20 -> GamerCurrentState.STAND
                21 -> GamerCurrentState.BLACKJACK
                else -> GamerCurrentState.BUST
            }
        }
    }
}
