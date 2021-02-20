package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Player.Companion.MAX_SCORE

class Game(playersName: List<String>) {
    val deck: Deck = Deck.createDeck()
    val players: List<Player> = playersName.map { Player(it, 10000) }
    val dealer: Dealer = Dealer()

    private var turn: Int = 0
    val turnPlayer: Player
        get() {
            return players[turn]
        }

    private var endPlayerTurn: Boolean = false
    val isEndPlayerTurn: Boolean
        get() = endPlayerTurn

    fun start() {
        for (player in players) {
            player.addCard(deck.draw())
            player.addCard(deck.draw())
        }
        dealer.addCard(deck.draw())
        dealer.addCard(deck.draw())
    }

    fun existBlackJack(): Boolean {
        return players.map { it.score() }
            .any { it == MAX_SCORE }
    }

    fun isEnableContinue(): Boolean {
        if (turnPlayer.isBust()) {
            changeNextPlayer()
        }
        return !endPlayerTurn
    }

    fun draw() {
        turnPlayer.addCard(deck.draw())
    }

    fun changeNextPlayer() {
        if (!existNextPlayer()) {
            endPlayerTurn()
        } else {
            turn += 1
        }
    }

    fun existNextPlayer(): Boolean {
        return turn < (players.size - 1)
    }

    fun isLastPlayer(): Boolean {
        return turn == (players.size - 1)
    }

    fun endPlayerTurn() {
        endPlayerTurn = true
    }

    fun playDealerTurn() {
        if (dealer.enableAdditionalDraw()) {
            dealer.addCard(deck.draw())
        }
    }
}
