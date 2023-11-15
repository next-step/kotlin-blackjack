package blackjack.view

import blackjack.domain.BlackJackDealerResult
import blackjack.domain.BlackJackPlayerResult
import blackjack.domain.Cards
import blackjack.domain.Suit

object OutputView {

    fun printPlayerName(playerNames: List<String>) {
        println("${playerNames.joinToString(", ")}$PRINT_INIT_CARD")
    }

    fun printPlayerFirstCard(result: List<BlackJackPlayerResult>) {
        result.forEach {
            println(nameDisplay(it.name) + cardDisplay(it.firstOpenCards))
        }
    }

    fun printFirstCard(result: BlackJackDealerResult) {
        println(nameDisplay(result.name) + cardDisplay(result.firstOpenCards))
    }

    fun printPlayerBurst(name: String) {
        println("$name$PLAYER_BURST")
    }

    fun printPlayerCard(result: BlackJackPlayerResult) {
        println(nameDisplay(result.name) + cardDisplay(result.cards))
    }

    fun printBlackjackGameResult(dealerResult: BlackJackDealerResult, playerResults: List<BlackJackPlayerResult>) {
        printBlackjackGameDealerResult(dealerResult)
        printBlackjackGamePlayerResult(playerResults)

        println(WIN_LOSE_RESULT)
        println("${dealerResult.name}: ${dealerResult.winLoseMoney.amount}")
        playerResults.forEach {
            println("${it.name}: ${it.winLoseMoney.amount}")
        }
    }

    fun printDealerDraw() {
        println(PRINT_DEALER_DRAW)
    }

    private fun printBlackjackGamePlayerResult(result: List<BlackJackPlayerResult>) {
        result.forEach {
            println(resultDisplay(it.name, it.cards, it.score.score))
        }
    }

    private fun printBlackjackGameDealerResult(result: BlackJackDealerResult) {
        println(resultDisplay(result.name, result.cards, result.score.score))
    }

    private const val PLAYER_BURST = "는 21점을 초과했습니다."
    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private const val PRINT_DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val WIN_LOSE_RESULT = "## 최종 수익"

    private fun resultDisplay(name: String, cards: Cards, score: Int): String {
        return "$name 카드: ${cardDisplay(cards)} 결과: $score"
    }

    private fun nameDisplay(name: String): String {
        return "$name 카드:"
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
