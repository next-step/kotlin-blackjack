package blackjack.view

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {
    fun showResultOfSetUp(players: Players?) {
        println("\n$players 에게 ${DEFAULT_BUFFER_SIZE}장의 카드를 나누었습니다.")
        println(players?.stateOfPlayerCard() + "\n")
    }

    fun showStateOfCards(player: Player, reply: String): Boolean {
        if (reply == "n" && player.amountOfCards() == Game.DEFAULT_CARD_SIZE) {
            println("$player 카드:" + player.stateCards())
        }
        if (reply == "y") {
            println("$player 카드:" + player.stateCards())
        }
        return false
    }

    fun showResult(result: String) {
        println("\n$result")
    }
}
