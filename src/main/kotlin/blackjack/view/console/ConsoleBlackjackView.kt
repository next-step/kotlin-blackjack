package blackjack.view.console

import blackjack.domain.Blackjack
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.BlackjackView

class ConsoleBlackjackView : BlackjackView {

    override fun printlnInitialPlayersCards(blackjack: Blackjack) {
        println("\n${blackjack.toPlayerListString()}에게 ${Player.INIT_CARD_COUNT}장의 카드를 나누었습니다.")
    }

    override fun printlnBlackjack(blackjack: Blackjack) {
        blackjack.players.forEach {
            printlnPlayer(it)
        }
    }

    override fun printlnBlackjackResult(blackjack: Blackjack) {
        blackjack.players.forEach {
            println(it.toResultString())
        }
    }

    override fun printlnPlayer(player: Player) {
        println(player.toContentString())
    }
}

fun Blackjack.toPlayerListString(): String =
    players.toContentString()

fun Players.toContentString(): String =
    names().joinToString(", ")

fun Player.toContentString(): String =
    "${name}카드: ${cards.toContentString()}"

fun Player.toResultString(): String =
    "${name}카드: ${cards.toContentString()} - 결과: $score"

fun Player.toCardListString(): String =
    cards.toContentString()

fun Cards.toContentString(): String =
    joinToString(", ") { card ->
        card.toContentString()
    }

fun Card.toContentString(): String =
    "${cardNumber.toContentString()}${cardShape.toContentString()}"

fun CardNumber.toContentString(): String = description

fun CardShape.toContentString(): String = description
