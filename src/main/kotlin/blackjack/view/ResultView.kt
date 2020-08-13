package blackjack.view

import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT
import blackjack.domain.Player
import blackjack.domain.Players

const val NOTIFY_EMPTY_DECK = "-- 더 이상 남아있는 카드가 없습니다 --"

object ResultView {

    fun showResultOfSetUp(players: Players) {
        println("\n${players}에게 2장의 카드를 나누었습니다")
        println(players.displayCurrentStates() + "\n")
    }

    fun showStateOfCards(player: Player?, reply: String): Boolean {
        if (player == null) {
            println("\n$NOTIFY_EMPTY_DECK")
            return true
        }
        if (REPLY_HIT == reply) {
            println("${player}카드: ${player.stateOfCards()}")
        }
        if (REPLY_STAND == reply && player.amountOfCards() == DEFAULT_CARD_AMOUNT) {
            println("${player}카드: ${player.stateOfCards()}")
        }
        return false
    }

    fun showGameResult(result: String) {
        println("\n$result")
    }
}
