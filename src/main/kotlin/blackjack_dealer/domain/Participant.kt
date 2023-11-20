package blackjack_dealer.domain

import blackjack_dealer.CardGenerator
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.GamerCurrentState

data class Participant(
    private val name: String,
    private val cards: GamerCards,
    private val currentState: GamerCurrentState = GamerCurrentState.INITIAL,
) {
    companion object {
        fun newInstance(name: String): Participant {
            val initialFirstCard = CardGenerator.generateSingleCard()
            val initialSecondCard = CardGenerator.generateSingleCard()
            val initialCards = GamerCards(listOf(initialFirstCard, initialSecondCard))

            val initialState = initialStateIsBlackJack(initialCards)

            return Participant(name = name, cards = initialCards, currentState = initialState)
        }

        private fun initialStateIsBlackJack(initialCards: GamerCards) =
            if (initialCards.sumOf { it.cardNumber.number } == 21) GamerCurrentState.BLACKJACK else GamerCurrentState.HIT
    }
}
