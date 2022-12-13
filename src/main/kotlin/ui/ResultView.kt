package ui

import model.BlackJackGame
import model.BlackJackScore
import model.Dealer
import model.Player
import model.Players

object ResultView {
    fun resultInitBlackJackGame(blackJackGame: BlackJackGame) {
        val playerNames = blackJackGame.getPlayers().map { it.name }
        println()
        println("${blackJackGame.dealer.name}와 ${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
        resultPlayerCard(blackJackGame.dealer)
        blackJackGame.getPlayers().forEach { player -> resultPlayerCard(player) }
    }

    fun resultPlayerCard(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }

    fun resultBlackJackGameScore(blackJackGame: BlackJackGame) {
        println()
        playerScore(blackJackGame.dealer)
        blackJackGame.getPlayers().forEach { player ->
            playerScore(player)
        }
    }

    private fun playerScore(player: Player) {
        println("${player.name} 카드: ${player.cards} - 결과 ${BlackJackScore(player.cards).score}")
    }

    fun resultProfit(blackJackGame: BlackJackGame) {
        println()
        println("## 최종 수익")

        dealerProfit(blackJackGame.players, blackJackGame.dealer)
        blackJackGame.getPlayers().forEach { player ->
            playerProfit(player, blackJackGame.dealer)
        }
    }

    private fun dealerProfit(players: Players, dealer: Dealer) {
        val dealerProfit = players.dealerProfit(dealer)
        println("${dealer.name} : $dealerProfit")
    }

    private fun playerProfit(player: Player, dealer: Dealer) {
        val profit = player.bettingReward(dealer)
        println("${player.name} : $profit ")
    }
}
