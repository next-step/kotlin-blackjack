package blackjack.domain.game

import blackjack.domain.deck.CardDeck
import blackjack.domain.player.HandStatus
import blackjack.domain.player.Participant
import blackjack.domain.player.Players

class Dealer : Participant() {
    private val cardDeck = CardDeck()

    fun initPlayersHand(players: Players) {
        for (i in 0 until INITIAL_DIVIDE_COUNT) {
            giveCardToAll(players)
            giveCardTo(this)
        }
    }

    fun giveCardTo(participant: Participant): HandStatus {
        return participant.receiveCard(cardDeck.pop())
    }

    fun fillHand(): Int {
        var count = 0
        while (getScore() <= DEALER_MORE_CARD_STANDARD) {
            giveCardTo(this)
            count++
        }
        return count
    }

    private fun giveCardToAll(players: Players) {
        players.participants.forEach { it.receiveCard(cardDeck.pop()) }
    }

    companion object {
        private const val INITIAL_DIVIDE_COUNT = 2
        private const val DEALER_MORE_CARD_STANDARD = 16
    }
}
