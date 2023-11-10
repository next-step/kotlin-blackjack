package blackjack.view

object OutputView {

    fun printInitCard(result: Pair<BlackJackDealerResult, List<BlackJackPlayerResult>>) {
        printPlayerName(result.second.map { it.playerName })
        printDealerFirstCard(result.first)
        printPlayersCard(result.second)
    }

    fun printPlayerBurst(name: String) {
        println("$name$PLAYER_BURST")
    }

    fun printPlayerCard(result: BlackJackPlayerResult) {
        println(result.cardDisplay)
    }

    fun printBlackjackGameResult(result: Pair<BlackJackDealerResult, List<BlackJackPlayerResult>>) {
        val dealerResult = result.first
        val playerResults = result.second
        dealerResult.run { println("$DEALER_NAME 카드: $cards - 결과: ${score.score}") }
        playerResults.forEach {
            println(it.resultDisplay)
        }

        println(WIN_LOSE_RESULT)
        println("$DEALER_NAME: ${dealerResult.winLose(playerResults.map { it.score })}")
        playerResults.forEach {
            println("${it.playerName}: ${it.winLose(dealerResult.score)}")
        }
    }

    fun printDealerDraw() {
        println(PRINT_DEALER_DRAW)
    }

    private fun printPlayerName(playerNames: List<String>) {
        println("${playerNames.joinToString(", ")}$PRINT_INIT_CARD")
    }

    private fun printPlayersCard(results: List<BlackJackPlayerResult>) {
        results.forEach {
            printPlayerCard(it)
        }
    }

    private fun printDealerFirstCard(result: BlackJackDealerResult) {
        println("$DEALER_NAME: ${result.firstCard}")
    }

    private const val PLAYER_BURST = "는 21점을 초과했습니다."
    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private const val PRINT_DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val WIN_LOSE_RESULT = "## 최종 승패"
    private const val DEALER_NAME = "딜러"
    private val BlackJackPlayerResult.cardDisplay get() = "${playerName}카드: $cards"
    private val BlackJackPlayerResult.resultDisplay get() = "$cardDisplay - 결과: ${score.score}"
}
