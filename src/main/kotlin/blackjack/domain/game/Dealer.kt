package blackjack.domain.game

import blackjack.domain.deck.CardDeck
import blackjack.domain.player.HandStatus
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class Dealer {
    private val cardDeck = CardDeck()

    fun initPlayersHand(players: Players) {
        for (i in 0 until INITIAL_DIVIDE_COUNT) {
            giveCardToAll(players)
        }
    }

    fun giveCardTo(player: Player): HandStatus {
        return player.receiveCard(cardDeck.pop())
    }

    private fun giveCardToAll(players: Players) {
        players.participants.forEach { it.receiveCard(cardDeck.pop()) }
    }

    companion object {
        private const val INITIAL_DIVIDE_COUNT = 2
    }
}
