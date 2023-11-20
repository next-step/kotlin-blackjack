package blackjack_dealer.domain

import blackjack_dealer.CardGenerator
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.GamerCurrentState

data class Participant(
    private val name: String,
    private val cards: GamerCards,
    private val currentState: GamerCurrentState = GamerCurrentState.INITIAL,
) {
    fun getCurrentCards(): GamerCards = cards
    fun getCurrentState(): GamerCurrentState = currentState

    companion object {
        fun newInstance(name: String, cards: GamerCards? = null): Participant {
            val initialFirstCard = CardGenerator.generateSingleCard()
            val initialSecondCard = CardGenerator.generateSingleCard()
            val initialCards = cards ?: GamerCards(listOf(initialFirstCard, initialSecondCard))

            val initialState = initialStateIsBlackJack(initialCards)

            return Participant(name = name, cards = initialCards, currentState = initialState)
        }

        private fun initialStateIsBlackJack(initialCards: GamerCards): GamerCurrentState =
            if (initialCards.getCurrentScore() == BLACK_JACK) GamerCurrentState.BLACKJACK else GamerCurrentState.HIT

        private const val BLACK_JACK = 21
    }
}
