package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object OutputView {
    private val messageBuilder = StringBuilder()

    fun printInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
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

    fun printFinalCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        messageBuilder.clear()
            .append("딜러 카드: ${dealer.cards().joinToString(", ")} - 결과: ${dealer.score()}\n")

        players.forEach { player ->
            messageBuilder.append("${player}카드: ${player.cards().joinToString(", ")} - 결과: ${player.score()}\n")
        }
        messageBuilder.append("\n")

        print(messageBuilder.toString())
    }

    fun printFinalProfits(
        dealerProfit: Double,
        playerProfits: Map<Player, Double>,
    ) {
        messageBuilder.clear()
            .append("\n## 최종 수익\n")
            .append("딜러: ${dealerProfit.toInt()}원\n")

        playerProfits.forEach { (player, profit) ->
            messageBuilder.append("$player: ${profit.toInt()}원\n")
        }

        print(messageBuilder.toString())
    }

    private fun print(message: String) {
        println(message)
    }
}
