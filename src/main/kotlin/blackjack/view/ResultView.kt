package blackjack.view

import blackjack.controller.GameTable
import blackjack.controller.GameTable.Companion.INIT_CARD_DRAW_COUNT
import blackjack.domain.player.Player

object ResultView {
    fun printDealerHit() = println("딜러는 16이하라 한장의 카드를 더 받았습니다.")

    fun printDealInitCard(gameTable: GameTable) {
        println("딜러와 ${gameTable.players.joinToString(", ") { it.name }}에게 ${INIT_CARD_DRAW_COUNT}장의 카드를 나누었습니다.")
        printPlayerCard(gameTable.dealer)
        printPlayersCard(gameTable.players)
        linebreak()
    }

    fun printAfterTurn(gameTable: GameTable) {
        printFinalHand(gameTable)
        printGameResult(gameTable)
    }

    fun printPlayerCard(
        player: Player,
        printScore: Boolean = false,
    ) {
        val cards = player.hand.cards.joinToString(", ") { "${it.rank.value}${it.suit.description}" }
        val scoreText = "- 결과: ${player.hand.score}"
        println("${player.name} 카드: $cards ${if (printScore) scoreText else ""}")
    }

    fun linebreak() = println()

    private fun printFinalHand(gameTable: GameTable) {
        linebreak()
        printPlayerCard(gameTable.dealer, printScore = true)
        printPlayersCard(gameTable.players, printScore = true)
    }

    private fun printGameResult(gameTable: GameTable) {
        linebreak()
        val gameResult = gameTable.getGameResult()
        val winMessage =
            if (gameResult.dealerGameResult.winCount > 0) "${gameResult.dealerGameResult.winCount}승" else ""
        val lossMessage =
            if (gameResult.dealerGameResult.loseCount > 0) "${gameResult.dealerGameResult.loseCount}패" else ""
        val drawMessage =
            if (gameResult.dealerGameResult.drawCount > 0) "${gameResult.dealerGameResult.drawCount}무" else ""
        println("## 최종 승패")
        println("딜러: $winMessage $lossMessage $drawMessage")
        gameResult.playerGameResults.forEach { println("${it.player.name}: ${it.result.description}") }
    }

    private fun printPlayersCard(
        players: List<Player>,
        printScore: Boolean = false,
    ) = players.forEach { printPlayerCard(it, printScore) }
}
