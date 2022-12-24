package blackjack.view

import blackjack.domain.Blackjack
import blackjack.domain.player.Player

class BlackjackView {
    fun printlnReadyToStart(blackjack: Blackjack) {
        println("\n${blackjack.players.names().joinToString(", ")}에게 " +
                "${Player.INIT_CARD_COUNT}장의 카드를 나누었습니다.")
    }
    
    fun printNewLine() = println()
}
