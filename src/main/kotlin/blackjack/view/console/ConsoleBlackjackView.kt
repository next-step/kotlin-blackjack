package blackjack.view.console

import blackjack.domain.Blackjack
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.player.CardHolder
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.betting.Profit
import blackjack.domain.player.result.PlayerResult
import blackjack.view.BlackjackView

class ConsoleBlackjackView : BlackjackView {

    override fun printlnInitialPlayersCards(dealer: Dealer, players: Players) {
        println("\n${dealer.name}와 ${players.toContentString()}에게 ${CardHolder.INIT_CARD_COUNT}장의 카드를 나누었습니다.")
    }

    override fun printlnBlackjack(blackjack: Blackjack) {
        printlnDealer(blackjack.dealer)

        blackjack.players.forEach {
            printlnPlayer(it)
        }
    }

    override fun printlnBlackjackResult(blackjack: Blackjack) {
        println(blackjack.dealer.toResultString())

        blackjack.players.forEach {
            println(it.toResultString())
        }

        println()
    }

    override fun printlnPlayer(player: Player) {
        println(player.toContentString())
    }

    override fun printlnDealer(dealer: Dealer) {
        println(dealer.toContentString())
    }

    override fun printlnBlackjackFinalResult(blackjack: Blackjack) {
        println("## 최종 승패")
        println(blackjack.dealer.toFinalResultString())
        println(blackjack.players.toFinalResultString())
        println()
    }

    override fun printlnDealerAddedCards(dealer: Dealer) {
        println("${dealer.name}는 ${Dealer.DEALER_REQUIRED_MIN_SCORE}이하라 한장의 카드를 더 받았습니다.\n")
    }

    override fun printlnBlackjackFinalProfit(blackjack: Blackjack) {
        println("## 최종 수익")
        println(blackjack.dealer.toProfitString())
        println(blackjack.players.toProfitString())
        println()
    }
}

fun Players.toContentString(): String =
    names().joinToString(", ")

fun Players.toFinalResultString(): String =
    joinToString("\n") { it.toFinalResultString() }

fun Players.toProfitString(): String =
    joinToString("\n") { it.toProfitString() }

fun CardHolder.toResultString(): String =
    "${name}카드: ${cards.toContentString()} - 결과: $score"

fun Player.toContentString(): String =
    "${name}카드: ${cards.toContentString()}"

fun Player.toFinalResultString(): String =
    "$name: ${finalResult.toContentString()}"

fun Player.toProfitString(): String =
    "$name: ${getProfit().toContentString()}"

fun Dealer.toContentString(): String =
    "$name: ${cards[0].toContentString()}"

fun Dealer.toFinalResultString(): String =
    "$name: ${getWinCount()}승 ${getLoseCount()}패"

fun Dealer.toProfitString(): String =
    "$name: ${getProfit().toContentString()}"

fun Cards.toContentString(): String =
    joinToString(", ") { card ->
        card.toContentString()
    }

fun Card.toContentString(): String =
    "${cardNumber.toContentString()}${cardShape.toContentString()}"

fun CardNumber.toContentString(): String = description

fun CardShape.toContentString(): String = description

fun PlayerResult.toContentString(): String = when (this) {
    PlayerResult.WIN -> "승"
    PlayerResult.LOSE -> "패"
    PlayerResult.DRAW -> "무"
    else -> throw UnsupportedOperationException("${PlayerResult.NOT_FINISHED}.toString() does not supported")
}

fun Profit.toContentString(): String =
    "${amount.toLong()}"
