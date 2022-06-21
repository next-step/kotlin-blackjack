package blackjack.view

import blackjack.model.Cards
import blackjack.model.Player
import blackjack.model.Players

object OutputView {

    fun printPlayerWithScore(player: Player) {
        println("${player.name}카드: ${cardsToString(player.cards)} - 결과 : ${player.cards.optimalScore().value}")
    }

    private fun cardsToString(cards: Cards): String {
        return cards.values
            .map { "${CardNumberView.toString(it.cardNumber)}${SuitView.toString(it.suit)}" }
            .joinToString(",")
    }

    fun printPlayersWithScore(players: Players) {
        players.values.forEach { printPlayerWithScore(it) }
    }

    fun printInitialState(players: Players) {
        println("${players.values.map { it.name }.joinToString(", ")}에게 2장의 카드를 나누어 주었습니다.")
        printPlayers(players)
        println()
    }

    fun printPlayers(players: Players) {
        players.values.forEach { printPlayer(it) }
        println()
    }

    fun printPlayer(player: Player) {
        println("${player.name}카드: ${cardsToString(player.cards)}")
    }
}
