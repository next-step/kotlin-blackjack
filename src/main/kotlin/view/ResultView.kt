package view

import blackjack.Game
import blackjack.Player
import blackjack.Players

object ResultView {

    fun showResultOfSetup(players: Players) {
        println("\n" + players + "에게 2장의 카드를 나누었습니다.")
        println(players.stateOfPlayerCard() + "\n")
    }

    fun showStateOfCards(player: Player, reply: String, game: Game): Boolean {
        if (reply == "y") {
            println("${player}카드: ${player.stateCards()}")
        }
        if (reply == "n" && player == game.lastPlayer()) {
            println("${player}카드: ${player.stateCards()}")
        }
        return false
    }

    fun showGameResult(result: String) {
        println("\n$result")
    }
}
