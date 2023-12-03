package blackjack.domain.participant

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.state.Start

class Dealer(
    private val deck: Deck,
): Participant(
    name = ParticipantName(DEALER_NAME),
    state = Start.handCard(deck.draw(), deck.draw())
) {

    fun drawCard(): Card = deck.draw()

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
