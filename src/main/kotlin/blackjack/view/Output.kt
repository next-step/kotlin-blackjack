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

    fun scoreCalculate(players: Players, dealer: Dealer) {
        println("${dealer.name}의 카드 : ${dealer.cardToString()} / 점수 : ${dealer.score()}")
        repeat(players.size()) {
            val player = players[it]
            println("${player.name}의 카드 : ${player.cardToString()} / 점수 : ${player.score()}")
        }
    }

    fun gameResult(players: Players, dealer: Dealer) {
        var dealerWinCount = 0
        var dealerDrawCount = 0
        repeat(players.size()) {
            val player = players[it]
            if (player is Dealer) return@repeat
            if (player.gameResult == GameResult.LOSE) {
                dealerWinCount += 1
                return@repeat
            }
            if (player.gameResult == GameResult.DRAW) {
                dealerDrawCount += 1
                return@repeat
            }
        }
        printResult(players, dealer, dealerWinCount, dealerDrawCount)
    }

    private fun printResult(players: Players, dealer: Dealer, dealerWinCount: Int, dealerDrawCount: Int) {
        println("${dealer.name} : ${dealerWinCount}승 ${players.size() - dealerWinCount - dealerDrawCount}패")
        repeat(players.size()) {
            val player = players[it]
            println("${player.name} : ${player.gameResult}")
        }
    }
}
