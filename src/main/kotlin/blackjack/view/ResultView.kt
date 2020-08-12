package blackjack.view

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.Gamer
import blackjack.model.Winner

object ResultView {

    fun printPreGame(game: BlackJackGame) {
        println()
        println("${game.dealer.name}와 ${game.players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        printDealerHaveCard(game.dealer)
        game.players.map { printPlayerHaveCard(it) }
    }

    fun printPlayerHaveCard(player: Gamer) {
        println("${player.name}카드: ${player.myReceivedCard.joinToString()}")
    }

    fun printResult(game: BlackJackGame) {
        if ((game.dealer as Dealer).isReceivedExtraCard()) println("딜러는 16이하라 한장의 카드를 더 받았습니다")
        println()
        println("${game.dealer.name} 카드: ${game.dealer.myReceivedCard.joinToString()} - 결과: ${game.dealer.totalPoints}")
        game.players.map { println("${it.name}카드: ${it.myReceivedCard.joinToString()} - 결과: ${it.totalPoints}") }
        println()
    }

    fun printFinalScore(game: BlackJackGame) {
        println("## 최종 승패")
        Winner.getTotalScore(game).mapIndexed { index, score ->
            if (index == 0) {
                println("${game.dealer.name}: ${score.win}승 ${score.lose}패")
                return@mapIndexed
            }
            println("${game.players[index - 1].name}: ${if (score.win == 1) "승" else "패"}")
        }
    }

    private fun printDealerHaveCard(dealer: Gamer) {
        println("${dealer.name}: ${dealer.myReceivedCard.first()}")
    }
}
