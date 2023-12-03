package blackjack.domain.participant

import blackjack.domain.Deck
import blackjack.domain.Score
import blackjack.domain.card.Card
import blackjack.domain.state.Started

class Dealer(
    private val deck: Deck,
): Participant(
    name = ParticipantName(DEALER_NAME),
    state = Started.handCard(deck.draw(), deck.draw())
) {

    fun handCard(): Card = deck.draw()

    fun canHit(): Boolean {
        return calculateScore() <= Score(MIN_HIT_SCORE)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        const val MIN_HIT_SCORE = 16
    }
}
