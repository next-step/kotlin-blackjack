package blackjack.view

import blackjack.domain.Game.Companion.INITIAL_CARD_COUNT
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.Players

object ConsoleOutput {
    fun printInitialCards(dealer: Player, players: Players) {
        println("달러와 ${players.list.joinToString { it.name.value }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
        printPlayerCards(dealer)
        players.list.map { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printDealerDrawOneMoreCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printResultCards(dealer: Player, players: Players) {
        printResultCards(dealer)
        players.list.map { printResultCards(it) }
        println()
    }

    fun printGameResult(dealer: Player, players: Players) {
        println("## 최종 승패")
        val dealerGameResult = groupingDealerResult(PlayerResult.ofDealer(dealer, players))
        println("딜러: ${dealerGameResult.joinToString()}")

        val playersResult = PlayerResult.ofGamePlayers(dealer, players)
        playersResult.map { println("${it.player.name.value}: ${it.gameResult[0].label}") }
    }

    private fun printResultCards(player: Player) {
        println("${getPlayerInfo(player)} - 결과: ${player.countingCard()}")
    }

    private fun getPlayerInfo(player: Player) = "${player.name.value} 카드: ${player.cards}"

    private fun groupingDealerResult(dealerResult: PlayerResult): Map<GameResult, Int> {
        return dealerResult.gameResult.groupingBy { it }.eachCount()
    }

    private fun Map<GameResult, Int>.joinToString(): String {
        return this.map { "${it.value}${it.key.label}" }.joinToString(" ")
    }

}
