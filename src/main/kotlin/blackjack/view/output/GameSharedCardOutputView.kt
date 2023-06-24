package blackjack.view.output

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Players

class GameSharedCardOutputView(players: Players) {
    init {
        val playerNames = players.value.joinToString(SEPARATOR) { it.name.value }
        println("${playerNames}에게 ${BlackjackGame.INIT_CARD_COUNT}장의 카드를 나누었습니다.")
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
