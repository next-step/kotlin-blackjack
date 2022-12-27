package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Game.Companion.INITIAL_CARD_COUNT
import blackjack.domain.GamePlayer
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.Players

object ConsoleOutput {
    fun printInitialCards(dealer: Player, players: Players) {
        println("달러와 ${players.list.joinToString { it.getPlayerName() }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
        printPlayerCards(dealer)
        players.list.map { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printDealerDrawOneMoreCard(dealer: Dealer) {
        if (dealer.cards.count() > INITIAL_CARD_COUNT) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            println()
        }
    }

    fun printGameResult(results: List<PlayerResult>) {
        val dealer = results.first { it.player is Dealer }
        val players = results.filter { it.player is GamePlayer }
        printResultCards(dealer, players)
        printResultProfit(dealer, players)
    }

    private fun printResultCards(dealerResult: PlayerResult, playerResult: List<PlayerResult>) {
        printResultCards(dealerResult.player)
        playerResult.map { printResultCards(it.player) }
        println()
    }

    private fun printResultProfit(dealerResult: PlayerResult, playerResults: List<PlayerResult>) {
        println("## 최종 수익")

        println("딜러: ${dealerResult.getProfit()}")
        playerResults.map { println("${it.player.getPlayerName()}: ${it.getProfit()}") }
    }

    private fun printResultCards(player: Player) {
        println("${getPlayerInfo(player)} - 결과: ${player.countingCard()}")
    }

    private fun getPlayerInfo(player: Player) = "${player.getPlayerName()} 카드: ${player.cards}"
}
