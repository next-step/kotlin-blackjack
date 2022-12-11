package ui

import model.BlackJackGame
import model.BlackJackScore
import model.Dealer
import model.Player
import model.Players

object ResultView {
    fun resultInitPokerGame(blackJackGame: BlackJackGame) {
        val playerNames = blackJackGame.getPlayers().map { it.name }
        println()
        println("${blackJackGame.dealer.name}와 ${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
        resultPlayerCard(blackJackGame.dealer)
        blackJackGame.getPlayers().forEach { player -> resultPlayerCard(player) }
    }

    fun resultPlayerCard(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }

    fun resultPokerGameScore(blackJackGame: BlackJackGame) {
        println()
        playerScore(blackJackGame.dealer)
        blackJackGame.getPlayers().forEach { player ->
            playerScore(player)
        }
    }

    private fun playerScore(player: Player) {
        println("${player.name} 카드: ${player.cards} - 결과 ${BlackJackScore(player.cards).score}")
    }

    fun resultFinalVictory(blackJackGame: BlackJackGame) {
        println()
        println("## 최종 승패")

        dealerWinOrLose(blackJackGame.players, blackJackGame.dealer)
        blackJackGame.getPlayers().forEach { player ->
            playerWinOrLose(player, blackJackGame.dealer)
        }
    }

    private fun dealerWinOrLose(players: Players, dealer: Dealer) {
        val competeResult = players.competeWith(dealer)
        println("딜러 : ${competeResult.win}승 ${competeResult.draw}무 ${competeResult.lose}패")
    }

    private fun playerWinOrLose(player: Player, dealer: Dealer) {
        val winOrLose = player.competeWith(dealer)
        println("${player.name} : ${winOrLose.description} ")
    }
}
