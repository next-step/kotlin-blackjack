package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.GameResult
import blackjack.domain.Hand
import blackjack.domain.Hand.Companion.INITIAL_HAND_SIZE
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit

object ResultView {
    private const val BUSTED = "🪦"

    fun Game.render(isInitial: Boolean = true) {
        val roster = players.roster
        val names = roster.map { it.name }
        val message =
            buildString {
                appendLine()
                if (isInitial) {
                    appendLine("딜러와 ${names.joinToString()}에게 2장의 나누었습니다.")
                }
                appendLine(dealer.format(isInitial))
                roster.forEach { appendLine(it.format(isInitial)) }
            }
        println(message)
    }

    fun Player.render() {
        println(format())
    }

    fun Dealer.renderActions() {
        val numberOfCardsDrawn = hand.cards.size - INITIAL_HAND_SIZE
        if (numberOfCardsDrawn == 0) {
            return
        }
        val message =
            buildString {
                appendLine()
                repeat(numberOfCardsDrawn) {
                    appendLine("딜러는 16이하라 한장의 카드를 더 받았습니다.")
                }
            }
        print(message)
    }

    fun Game.renderResults() {
        // 게임 최종 상태 출력
        render(false)
        // 수익 출력
        gameResult().render()
    }

    private fun GameResult.render() {
        val message =
            buildString {
                appendLine("## 최종 수익")
                appendLine("딜러: ${dealerProfit()}")
                playerResults.forEach {
                    appendLine("${it.name}: ${it.profit()}")
                }
            }
        println(message)
    }

    private fun Player.format(isInitial: Boolean = true): String {
        val result = "${name}카드: ${hand.format()}"
        if (isInitial) {
            return result
        }
        return result + " - 결과: ${if (isBusted) BUSTED else value}"
    }

    private fun Dealer.format(isInitial: Boolean): String {
        val result = "딜러: ${hand.format()}"
        if (isInitial) {
            return result
        }
        return result + " - 결과: ${if (isBusted) BUSTED else value}"
    }

    private fun Hand.format(): String = cards.filter { it.isFaceUp }.joinToString { it.format() }

    private fun Card.format(): String = "${rank.format()}${suit.format()}"

    private fun Rank.format(): String =
        when (this) {
            Rank.ACE -> "A"
            Rank.TWO -> "2"
            Rank.THREE -> "3"
            Rank.FOUR -> "4"
            Rank.FIVE -> "5"
            Rank.SIX -> "6"
            Rank.SEVEN -> "7"
            Rank.EIGHT -> "8"
            Rank.NINE -> "9"
            Rank.TEN -> "10"
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
        }

    private fun Suit.format(): String =
        when (this) {
            Suit.HEARTS -> "하트"
            Suit.DIAMONDS -> "다이아몬드"
            Suit.CLUBS -> "클로버"
            Suit.SPADES -> "스페이드"
        }
}
