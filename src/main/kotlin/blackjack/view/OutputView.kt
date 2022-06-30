package blackjack.view

import blackjack.model.BlackjackGame
import blackjack.model.Cards
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Results

object OutputView {

    private fun printPlayerWithScore(player: Player) {
        println("${player.name}카드: ${cardsToString(player.cards)} - 결과 : ${player.cards.optimalScore().value}")
    }

    private fun cardsToString(cards: Cards, skip: Int = 0): String {
        return cards.values
            .drop(skip)
            .map { "${CardNumberView.toString(it.cardNumber)}${SuitView.toString(it.suit)}" }
            .joinToString(",")
    }

    fun printInitialState(game: BlackjackGame) {
        println("${game.dealer.name}와 ${game.players.values.map { it.name }.joinToString(", ")}에게 2장의 카드를 나누어 주었습니다.")
        printDealer(game.dealer)
        printPlayers(game.players)
        println()
    }

    fun printFinalState(game: BlackjackGame) {
        printPlayerWithScore(game.dealer)
        game.players.values.forEach { printPlayerWithScore(it) }
    }

    fun printWinners(results: Results) {
        println("\n ## 최종 승패")
        println("${results.dealerResult.player.name} : ${results.dealerResult.win}승 ${results.dealerResult.lose}패")
        results.playerResults.forEach { playerResult ->
            println("${playerResult.player.name} : ${if (playerResult.win) "승" else "패"}")
        }
    }

    private fun printPlayers(players: Players) {
        players.values.forEach { printPlayer(it) }
        println()
    }

    fun printPlayer(player: Player) {
        println("${player.name}카드: ${cardsToString(player.cards)}")
    }

    fun printDealerHit() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printDealer(player: Player) {
        println("${player.name}카드: ${cardsToString(player.cards, skip = 1)}")
    }
}
