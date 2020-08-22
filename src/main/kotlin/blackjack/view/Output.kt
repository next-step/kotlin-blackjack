package blackjack.view

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players

object Output {
    fun players(players: Players) {
        println("${players}에게 2장의 나누었습니다.")
    }

    fun pickResult(player: Player) {
        println("${player.name}의 카드 : ${player.cardToString()}")
    }

    fun dealerAddCard(isDraw: Boolean) {
        if (isDraw) println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun scoreCalculate(players: Players) {
        repeat(players.size()) {
            val player = players[it]
            println("${player.name}의 카드 : ${player.cardToString()} / 점수 : ${player.score()}")
        }
    }

    fun gameResult(players: Players) {
        var dealerWinCount = players.dealerExceptionSize()
        repeat(players.size()) {
            val player = players[it]
            if (player is Dealer) return@repeat
            if (player.gameResult == GameResult.WIN) dealerWinCount -= 1
        }

        repeat(players.size()) {
            val player = players[it]
            if (player is Dealer) println("${player.name} : ${dealerWinCount}승 ${players.dealerExceptionSize() - dealerWinCount}패")
            if (player !is Dealer) println("${player.name} : ${player.gameResult}")
        }
    }
}
