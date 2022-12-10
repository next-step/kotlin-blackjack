package ui

import model.Dealer
import model.Player
import model.Players
import model.PokerGame
import model.PokerScore

object ResultView {
    fun resultInitPokerGame(pokerGame: PokerGame) {
        val playerNames = pokerGame.getPlayers().map { it.name }
        println()
        println("${pokerGame.dealer.name}와 ${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
        resultPlayerCard(pokerGame.dealer)
        pokerGame.getPlayers().forEach { player -> resultPlayerCard(player) }
    }

    fun resultPlayerCard(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }

    fun resultPokerGameScore(pokerGame: PokerGame) {
        println()
        playerScore(pokerGame.dealer)
        pokerGame.getPlayers().forEach { player ->
            playerScore(player)
        }
    }

    private fun playerScore(player: Player) {
        println("${player.name} 카드: ${player.cards} - 결과 ${PokerScore(player.cards).score}")
    }

    fun resultFinalVictory(pokerGame: PokerGame) {
        println()
        println("## 최종 승패")

        dealerWinOrLose(pokerGame.players, pokerGame.dealer)
        pokerGame.getPlayers().forEach { player ->
            playerWinOrLose(player, pokerGame.dealer)
        }
    }

    private fun dealerWinOrLose(players: Players, dealer: Dealer) {
        val competeResult = players.competeWith(dealer)
        println("딜러 : ${competeResult.win}승  ${competeResult.lose}패")
    }

    private fun playerWinOrLose(player: Player, dealer: Dealer) {
        val winOrLose = player.competeWith(dealer)
        println("${player.name} : ${winOrLose.description} ")
    }
}
