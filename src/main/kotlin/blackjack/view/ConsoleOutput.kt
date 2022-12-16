package blackjack.view

import blackjack.domain.Deck.Companion.INITIAL_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.PlayersResult

object ConsoleOutput {
    fun printInitialCards(dealer: Player, players: Players) {
        println("달러와 ${players.list.joinToString { it.name.value }}에게 ${INITIAL_CARD_COUNT}장의 카드를 나누었습니다.")
        printPlayerCards(dealer)
        players.list.map { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player))

    fun printResultCards(playersResult: PlayersResult) {
        printResultCards(playersResult.dealer)
        playersResult.players.list.map { printResultCards(it) }
        println()
    }

    fun printGameResult(playersResult: PlayersResult) {
        println("## 최종 승패")
        println("딜러: ${playersResult.getDealerResult().map { "${it.value}${it.key.label}" }.joinToString(" ")}")
        playersResult.getGamePlayersResult().map { println("${it.key.name.value}: ${it.value.label}") }
    }

    private fun printResultCards(player: Player) {
        println("${getPlayerInfo(player)} - 결과: ${player.countingCard()}")
    }

    private fun getPlayerInfo(player: Player) = "${player.name.value} 카드: ${player.cards}"
}
