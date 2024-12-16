package blackjack.domain

import blackjack.domain.Participant.Player

class Dealer(
    private val deck: Deck,
    private val displayName: String = "Dealer",
) : Player(displayName) {
    fun deal(participants: Participants) {
        repeat(NUMBER_OF_DEAL_CARD) {
            dealOneCardToEachPlayer(participants)
        }
    }

    private fun dealOneCardToEachPlayer(participants: Participants) {
        participants.allPlayers().forEach {
            it.receiveCard(deck.draw())
        }
    }

    fun hit(player: Player) {
        player.receiveCard(deck.draw())
    }

    companion object {
        private const val NUMBER_OF_DEAL_CARD = 2
    }
}
