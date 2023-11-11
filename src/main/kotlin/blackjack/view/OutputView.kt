package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Suit
import blackjack.domain.WinLose

object OutputView {

    fun printPlayerName(playerNames: List<String>) {
        println("${playerNames.joinToString(", ")}$PRINT_INIT_CARD")
    }
    fun printPlayerFirstCard(result: List<BlackJackPlayerResult>) {
        result.forEach {
            printFirstCard(it)
        }
    }

    fun printFirstCard(result: BlackJackPlayerResult) {
        println(result.printName + cardDisplay(result.firstOpenCards))
    }

    fun printPlayerBurst(name: String) {
        println("$name$PLAYER_BURST")
    }

    fun printPlayerCard(result: BlackJackPlayerResult) {
        println(result.printName + cardDisplay(result.cards))
    }

    fun printBlackjackGamePlayerResult(result: List<BlackJackPlayerResult>) {
        result.forEach {
            printBlackjackGameResult(it)
        }
    }

    fun printBlackjackGameResult(result: BlackJackPlayerResult) {
        println(result.resultDisplay)
    }
//    fun printBlackjackGameResult(result: Pair<BlackJackDealerResult, List<BlackJackPlayerResult>>) {
//        val dealerResult = result.first
//        val playerResults = result.second
//        dealerResult.run { println(resultDisplay) }
//        playerResults.forEach {
//            println(it.resultDisplay)
//        }
//
//        println(WIN_LOSE_RESULT)
//        println("${dealerResult.name}: ${dealerResult.winLose(playerResults.map { it.score })}")
//        playerResults.forEach {
//            println("${it.playerName}: ${it.winLose(dealerResult.score)}")
//        }
//    }

    fun printDealerDraw() {
        println(PRINT_DEALER_DRAW)
    }

    private const val PLAYER_BURST = "는 21점을 초과했습니다."
    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private const val PRINT_DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val WIN_LOSE_RESULT = "## 최종 승패"
    private val BlackJackPlayerResult.printName get() = "$name 카드:"
    private val BlackJackPlayerResult.resultDisplay get() = "$printName${cardDisplay(cards)} 결과: ${score.score}"

    private fun WinLose.name(): String {
        return when (this) {
            WinLose.WIN -> "승"
            WinLose.LOSE -> "패"
            WinLose.DRAW -> "무"
        }
    }
    private fun cardDisplay(cards: Cards): String {
        return cards.cards.joinToString(", ") { card ->
            "${card.rank.rankName}${card.suit.suitName()}"
        }
    }

    private fun Suit.suitName(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }
}
