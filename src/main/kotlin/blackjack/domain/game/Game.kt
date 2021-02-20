package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Player.Companion.MAX_SCORE

class Game(players: List<Player>, val deck: Deck) {
    private var _playersInGame = players.toMutableList()
    val playersInGame: List<Player>
        get() = _playersInGame.toList()
    private val _playersOutOfGame = mutableListOf<Player>()
    val playersOutOfGame: List<Player>
        get() = _playersOutOfGame.toList()
    val dealer: Dealer = Dealer()

    private var turn: Int = 0
    val turnPlayer: Player
        get() {
            return playersInGame[turn]
        }

    private var endPlayerTurn: Boolean = false

    fun start() {
        for (player in playersInGame) {
            player.addCard(deck.draw())
            player.addCard(deck.draw())
        }
        dealer.addCard(deck.draw())
        dealer.addCard(deck.draw())

        val blackJackPlayers = _playersInGame.filter { it.score() == MAX_SCORE }
        _playersOutOfGame.addAll(blackJackPlayers)
        _playersInGame = playersInGame.filterNot { it.score() == MAX_SCORE }.toMutableList()
    }

    fun isEnableContinue(): Boolean {
        if (turnPlayer.isBust()) {
            changeNextPlayer()
        }
        return !endPlayerTurn
    }

    private fun draw() {
        turnPlayer.addCard(deck.draw())
    }

    private fun changeNextPlayer() {
        if (!existNextPlayer()) {
            endPlayerTurn()
        } else {
            turn += 1
        }
    }

    private fun existNextPlayer(): Boolean {
        return turn < (playersInGame.size - 1)
    }

    private fun endPlayerTurn() {
        endPlayerTurn = true
    }

    fun playDealerTurn() {
        if (dealer.enableAdditionalDraw()) {
            dealer.addCard(deck.draw())
        }
    }

    fun playOneStep(additionalDraw: (String) -> Boolean, forEachStep: (Player) -> Unit) {
        if (additionalDraw(turnPlayer.name)) {
            draw()
            forEachStep(turnPlayer)
        } else {
            changeNextPlayer()
        }
    }
}
