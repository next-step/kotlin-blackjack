package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

const val BLACKJACK_NUMBER = 21
const val ACE_HIDDEN_VALUE = 10
const val START_DRAW_COUNT = 2

class BlackJackGame private constructor(
    private val deck: Deck,
    private val players: Players
) {
    fun start() {
        players.players.forEach {
            it.drawStartHand(deck)
        }
    }

    fun bet(continueGame: (Player) -> Boolean, afterDrawCard: (Player) -> Unit) {
        players.race(deck.popCard(), continueGame, afterDrawCard)
    }

    companion object {
        fun create(players: Players) = BlackJackGame(Deck.makeDeck(), players)
    }
}
