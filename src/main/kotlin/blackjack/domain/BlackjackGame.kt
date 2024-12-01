package blackjack.domain

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackjackGame(
    private val deck: Deck,
    dealer: Dealer,
    players: List<Player>,
) {
    val participants: List<Participant> = listOf(dealer).plus(players)

    fun start() {
        participants.forEach { participant ->
            repeat(START_DRAW_COUNT) { draw(participant) }
        }
    }

    fun draw(participant: Participant) {
        participant.receivedCard(deck.draw())
    }

    companion object {
        private const val START_DRAW_COUNT = 2
    }

}
