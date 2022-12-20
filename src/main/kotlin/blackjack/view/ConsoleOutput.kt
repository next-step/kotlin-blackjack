package blackjack.view

import blackjack.domain.Game.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.Players
import blackjack.domain.Profit

object ConsoleOutput {
    fun printInitialCards(dealer: Player, players: Players) {
        println("달러와 ${players.list.joinToString { it.getPlayerName() }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
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
        println("## 최종 수익")

        val playersResult = PlayerResult.ofGamePlayers(dealer, players)
        println("딜러: ${Profit.ofDealer(playersResult)}")
        playersResult.map { println("${it.player.getPlayerName()}: ${it.getProfit()}") }
    }

    private fun printResultCards(player: Player) {
        println("${getPlayerInfo(player)} - 결과: ${player.countingCard()}")
    }

    private fun getPlayerInfo(player: Player) = "${player.getPlayerName()} 카드: ${player.cards}"
}
