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
        dealerResult.run { println("딜러 카드: $cards - 결과: ${score.score}") }
        playerResults.forEach {
            println(it.resultDisplay)
        }

        println("## 최종 승패")
        println("딜러: ${dealerResult.winLose(playerResults.map { it.score })}")
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
        println("딜러: ${result.firstCard}")
    }

    private const val PLAYER_BURST = "는 21점을 초과했습니다."

    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private const val PRINT_DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private val BlackJackPlayerResult.cardDisplay get() = "${playerName}카드: $cards"
    private val BlackJackPlayerResult.resultDisplay get() = "$cardDisplay - 결과: ${score.score}"
}
