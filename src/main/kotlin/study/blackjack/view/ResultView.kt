package study.blackjack.view

import study.blackjack.model.BlackjackUser
import study.blackjack.model.CardRank
import study.blackjack.model.Match
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class ResultView {
    fun printInitGiveCardsMessage(
        players: List<BlackjackUser>,
        cardCount: Int,
    ) {
        println(players.joinToString(", ") { it.name })
        println()
        println("딜러와 ${players.joinToString(", ") { it.name }} 에게 ${cardCount}장의 카드를 나누었습니다.")
    }

    fun printInitCardOfPlayer(player: BlackjackUser) {
        println("${player.name} 카드: ${player.cards().toList().joinToString(", ") { cardName(it.cardRank, it.suit) }}")
    }

    fun printResultCardOfPlayer(player: BlackjackUser) {
        val cards = player.cards().toList().joinToString(", ") { cardName(it.cardRank, it.suit) }
        val score = player.cards().calculateScore()
        println("${player.name} 카드: $cards - 결과: $score")
    }

    fun printAddCardOfDealerMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printFinalStats(players: List<BlackjackUser>) {
        println()
        println("## 최종 승패")
        val win = players.count { it.result() == Match.LOSE }
        val lose = players.count { it.result() == Match.WIN }
        println("딜러: ${win}승 ${lose}패")
        players.forEach {
            println("${it.name}: ${it.result()}")
        }
    }

    private fun cardName(cardRank: CardRank, suit: Suit): String {
        return "%s%s".format(cardRankName(cardRank), suitName(suit))
    }

    private fun cardRankName(cardRank: CardRank): String {
        return when (cardRank) {
            CardRank.ACE -> "A"
            CardRank.JACK -> "J"
            CardRank.QUEEN -> "Q"
            CardRank.KING -> "K"
            else -> (cardRank.ordinal + 1).toString()
        }
    }

    private fun suitName(suit: Suit): String {
        return when (suit) {
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.SPADE -> "스페이드"
            Suit.CLUB -> "클로버"
            else -> throw IllegalArgumentException()
        }
    }
}
