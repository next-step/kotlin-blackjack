package ui

import model.Player
import model.PokerGame
import model.PokerScore

object ResultView {
    fun resultInitPokerGame(pokerGame: PokerGame) {
        val playerNames = pokerGame.getPlayers().map { it.name }
        println()
        println("${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
        pokerGame.getPlayers().forEach { player -> resultPlayerCard(player) }
    }

    fun resultPlayerCard(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }

    fun resultPokerGameScore(pokerGame: PokerGame) {
        println()
        pokerGame.getPlayers().forEach { player ->
            println("${player.name} 카드: ${player.cards} - 결과 ${PokerScore(player.cards).score()}")
        }
    }
}
