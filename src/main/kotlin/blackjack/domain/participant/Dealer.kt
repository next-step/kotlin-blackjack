package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.participant.state.Init
import blackjack.domain.participant.state.Score

class Dealer(
    private val deck: Deck = Deck.release(),
) : Participant(
    state = Init.receiveCard(firstCard = deck.drawCard(), secondCard = deck.drawCard())
) {
    fun drawCard(): Card = deck.drawCard()

    fun isOverThenLimitScore(): Boolean = state.score() >= LIMIT_SCORE

    companion object {
        private val LIMIT_SCORE = Score(17)
    }
}
