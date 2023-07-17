package blackjack.view

import blackjack.domain.BlackjackResult
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.DealerResult
import blackjack.domain.Hands
import blackjack.domain.Player
import blackjack.domain.PlayerResult

object ResultView {
    fun printInit(players: List<Player>) {
        println()
        println("${players.joinToString(", ") { it.name }}에게 ${Hands.INIT_CARD_SIZE}장의 카드를 나누었습니다.")
        players.forEach { printPlayerInfo(it) }
        println()
    }

    fun printPlayerInfo(player: Player) {
        println(playerInfo(player))

        if (player.isBust()) {
            println("${player.name} Bust!!")
        }
    }

    fun printResult(blackjackResult: BlackjackResult) {
        println()
        printPlayerInfoWithScore(blackjackResult.dealer)
        blackjackResult.players.forEach { printPlayerInfoWithScore(it) }

        println()
        println("## 최종 승패")
        println(dealerResult(blackjackResult.dealerResult))
        blackjackResult.playerResults
            .forEach { println(playerResult(it)) }
    }

    fun printDealerHit() {
        println()
        println("딜러는 ${Dealer.HIT_THRESHOLD}이하라 한장의 카드를 더 받았습니다.")
    }

    private fun printPlayerInfoWithScore(player: Player) {
        println("${playerInfo(player)} - 결과: ${player.hands.sum()}")
    }

    private fun playerInfo(player: Player): String {
        return "${player.name}카드: ${player.hands.cards.joinToString(", ") { cardInfo(it) }}"
    }

    private fun cardInfo(card: Card): String {
        return "${card.symbol.displayName}${card.type.displayName}"
    }

    private fun dealerResult(dealerResult: DealerResult): String {
        val resultDisplayNames = dealerResult.result
            .entries
            .map { "${it.value}${it.key.displayName}" }

        return "${dealerResult.dealerName()}: ${resultDisplayNames.joinToString(" ")}"
    }

    private fun playerResult(playerResult: PlayerResult): String {
        return "${playerResult.playerName()}: ${playerResult.resultDisplayName()}"
    }
}
