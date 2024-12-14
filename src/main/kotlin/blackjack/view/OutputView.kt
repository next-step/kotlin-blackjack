package blackjack.view

import blackjack.BlackJackGame
import blackjack.participant.Player

object OutputView {
    fun printPlayersStartCardPack(game: BlackJackGame) {
        val dealer = game.dealer
        val gamePlayer = game.gamePlayers.player
        println("\n${dealer.name.value}와 ${gamePlayer.joinToString { it.name.value }}에게 2장의 카드를 나누었습니다.")
        println("${dealer.name.value}: ${dealer.cards.getOpenCard()}")
        gamePlayer.forEach { println("$it") }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(player)
    }

    fun printBlackJackResult(players: List<Player>) {
        println()
        players.forEach { println("$it - 결과:${it.score()}") }
    }
}
