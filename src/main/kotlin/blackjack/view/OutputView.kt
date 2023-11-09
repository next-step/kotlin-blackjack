package blackjack.view

object OutputView {

    fun printInitCard(playerNames: List<String>) {
        println("${playerNames.joinToString(", ")}$PRINT_INIT_CARD")
    }

    fun printPlayersCard(results: List<BlackJackGameResult>) {
        results.forEach {
            printPlayerCard(it)
        }
    }

    fun printPlayerBurst(name: String) {
        println("$name$PLAYER_BURST")
    }

    fun printPlayerCard(result: BlackJackGameResult) {
        println(result.cardDisplay)
    }

    fun printBlackjackGameResult(result: List<BlackJackGameResult>) {
        result.forEach {
            println(it.resultDisplay)
        }
    }

    private const val PLAYER_BURST = "는 21점을 초과했습니다."

    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private val BlackJackGameResult.cardDisplay get() = "${playerName}카드: $cards"
    private val BlackJackGameResult.resultDisplay get() = "$cardDisplay - 결과: $score"
}
