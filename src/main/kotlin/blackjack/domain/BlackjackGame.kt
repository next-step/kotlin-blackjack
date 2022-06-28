package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackjackGame(
    private val deck: Deck,
    val players: Players,
) {
    fun start() {
        players.drawInitCards(deck)
    }

    fun isPlaying(): Boolean {
        return players.isExistWaitingPlayer()
    }

    fun askDrawToCurrentTurnPlayer(isDrawCard: Boolean) {
        val player = players.findCurrentTurnPlayer()
        if (isDrawCard) {
            player.drawCard(deck.pullOut())
            return
        }
        player.endOwnTurn()
    }

    fun findCurrentTurnPlayer(): Player = players.findCurrentTurnPlayer()
}
