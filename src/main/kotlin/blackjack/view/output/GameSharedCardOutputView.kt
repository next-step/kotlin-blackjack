package blackjack.view.output

import blackjack.domain.BlackjackGame
import blackjack.domain.player.GamePlayers

class GameSharedCardOutputView(gamePlayers: GamePlayers) {
    init {
        val playerNames = gamePlayers.players.joinToString(SEPARATOR) { it.name.value }
        println("딜러와 ${playerNames}에게 ${BlackjackGame.INIT_CARD_COUNT}장의 카드를 나누었습니다.")
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
