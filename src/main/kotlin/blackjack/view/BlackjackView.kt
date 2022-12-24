package blackjack.view

import blackjack.domain.Blackjack
import blackjack.domain.player.Player

class BlackjackView(
    private val playerView: PlayerView
) {
    fun printlnBeforeStart(blackjack: Blackjack) {
        println("\n${blackjack.players.names().joinToString(", ")}에게 " +
                "${Player.INIT_CARD_COUNT}장의 카드를 나누었습니다.")
    }

    fun printlnResult(blackjack: Blackjack) {
        println()
        blackjack.players.forEach {
            playerView.printlnPlayerResult(it)
        }
    }
}
