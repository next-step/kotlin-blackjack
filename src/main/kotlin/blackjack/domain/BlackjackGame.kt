package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackjackGame(
    private val deck: Deck,
    val players: Players,
) {
    fun init() {
        players.drawInitCards(deck)
    }

    fun isPlaying(): Boolean {
        return players.isExistWaitingPlayer()
    }

    fun askDrawToCurrentTurnPlayer(isDrawCard: Boolean) { // TODO 2022-06-25 경록: 테스트 코드 작성하기
        val player = players.findCurrentTurnPlayer()
        if (isDrawCard) {
            player.drawCard(deck.pullOut())
            return
        }
        player.endOwnTurn()
    }

    fun findCurrentTurnPlayer(): Player = players.findCurrentTurnPlayer()
}
