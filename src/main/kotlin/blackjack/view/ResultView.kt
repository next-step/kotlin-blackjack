package blackjack.view

import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT
import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {

    fun showResultOfSetUp(players: Players) {
        println("\n${players}에게 2장의 카드를 나누었습니다")
        println(players.displayCurrentStates() + "\n")
    }

    fun showStateOfCards(player: Player, reply: String) {
        if (REPLY_RECEIVE == reply) {
            println("${player}카드: ${player.displayCards()}")
        }
        if (REPLY_REJECT == reply && player.amountOfCards() == DEFAULT_CARD_AMOUNT) {
            println("${player}카드: ${player.displayCards()}")
        }
    }

    fun showGameResult(result: String) {
        println("\n$result")
    }
}
