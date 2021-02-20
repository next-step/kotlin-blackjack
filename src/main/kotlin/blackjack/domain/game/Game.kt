package blackjack.domain.game

import blackjack.domain.deck.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Player.Companion.MAX_SCORE

class Game(playersName: List<String>) {
    val deck: Deck = Deck.createDeck()
    val players: List<Player> = playersName.map { Player(it) }
    val dealer: Dealer = Dealer()

    private var turn: Int = 0
    val turnPlayer: Player
        get() {
            return players[turn]
        }

    private var end: Boolean = false
    val isEnd: Boolean
        get() = end

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
        if (turnPlayer.isBurst()) {
            return false
        }
        return true
    }

    fun draw() {
        turnPlayer.addCard(deck.draw())
    }

    fun changeNextPlayer() {
        require(existNextPlayer()) { "다음 게임을 진행할 플레이어가 없습니다." }
        turn += 1
    }

    fun existNextPlayer(): Boolean {
        return turn < (players.size - 1)
    }

    fun isLastPlayer(): Boolean {
        return turn == (players.size - 1)
    }

    fun endGame() {
        end = true
    }

    fun playDealerTurn() {
        if (dealer.additionalDraw()) {
            dealer.addCard(deck.draw())
        }
    }
}
