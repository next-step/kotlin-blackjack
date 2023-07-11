package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

const val BLACKJACK_NUMBER = 21
const val ACE_HIDDEN_VALUE = 10
const val START_DRAW_COUNT = 2

class BlackJackGame private constructor(
    private val deck: Deck,
) {
    fun prepare(players: Players) {
        players.players.forEach {
            it.drawStartHand(deck)
        }
    }

    fun race(players: Players, continueGame: (Player) -> Boolean, afterDrawCard: (Player) -> Unit) {
        players.players.forEach {
            it.race(deck, continueGame, afterDrawCard)
        }
    }

    companion object {
        fun create() = BlackJackGame(Deck.makeDeck())
    }
}
