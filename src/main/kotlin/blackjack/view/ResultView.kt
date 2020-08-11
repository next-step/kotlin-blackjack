package blackjack.view

import blackjack.model.BlackJackGame
import blackjack.model.Gamer

object ResultView {

    fun printPreGame(game: BlackJackGame) {
        println("${game.dealer.name}와 ${game.players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        game.players.map { printPlayerHaveCard(it) }
    }

    fun printPlayerHaveCard(player: Gamer) {
        println("${player.name}카드: ${player.myReceivedCard.joinToString()}")
    }

    fun printResult(player: Gamer, point: Int) {
        println("${player.name}카드: ${player.myReceivedCard.joinToString()} - 결과: $point")
    }
}
