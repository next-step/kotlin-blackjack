package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {

    fun displayCards(players: Players) {
        players.players.forEach { player ->
            displayCards(player)
        }
    }

    fun displayCards(player: Player) {
        print("${player.getName()}카드: ")
        println(player.getCards().toString())
    }

    fun displayGameResult(players: Players) {
        players.players.forEach { player ->
            println("${player.getName()}카드: ${player.getCards()} - 결과: ${player.getScore()}")
        }
    }
}
