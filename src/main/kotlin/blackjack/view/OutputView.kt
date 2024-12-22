package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.state.GameResult

object OutputView {
    private val messageBuilder = StringBuilder()

    fun printInitialCards(dealer: Dealer, players: List<Player>) {
        messageBuilder.clear()
            .append("\n딜러와 ")
            .append(players.joinToString(", ") { it.toString() })
            .append(" 에게 2장의 카드를 나누었습니다.\n")
            .append("딜러: ${dealer.cards()}\n")

        players.forEach { player ->
            messageBuilder.append("${player}카드: ${player.cards().joinToString(", ")}\n")
        }
        messageBuilder.append("\n")

        print(messageBuilder.toString())
    }

    fun printPlayerCards(player: Player) {
        messageBuilder.clear()
            .append("${player}카드: ${player.cards().joinToString(", ")}\n")

        print(messageBuilder.toString())
    }

    fun printDealerDrawMessage() {
        messageBuilder.clear()
            .append("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n\n")

        print(messageBuilder.toString())
    }

    fun printFinalCards(dealer: Dealer, players: List<Player>) {
        messageBuilder.clear()
            .append("딜러 카드: ${dealer.cards().joinToString(", ")} - 결과: ${dealer.score()}\n")

        players.forEach { player ->
            messageBuilder.append("${player}카드: ${player.cards().joinToString(", ")} - 결과: ${player.score()}\n")
        }
        messageBuilder.append("\n")

        print(messageBuilder.toString())
    }

    fun printFinalResults(dealerResult: Map<GameResult, Int>, playerResults: Map<Player, GameResult>) {
        val dealerWins = dealerResult[GameResult.WIN] ?: 0
        val dealerLoses = dealerResult[GameResult.LOSE] ?: 0

        messageBuilder.clear()
            .append("## 최종 승패\n")
            .append("딜러: ${dealerWins}승 ${dealerLoses}패\n")

        playerResults.forEach { (player, result) ->
            messageBuilder.append("${player}: ${result.toString().lowercase()}\n")
        }

        print(messageBuilder.toString())
    }

    private fun print(message: String) {
        println(message)
    }
}
