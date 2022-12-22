package blackjack.ui

import blackjack.controller.Casino
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.Participant
import blackjack.domain.Suit

class ResultView {

    fun showPlayers(casino: Casino) {
        val names = casino.names()
        println("${names}에게 2장의 나누었습니다.")
        casino.printAllPlayers { player -> player.print() }
    }

    fun ask(): (Participant) -> String = { player ->
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        readlnOrNull() ?: ""
    }

    fun showPlayerCards(): (Participant) -> Unit = { player -> player.print() }

    fun showResult(casino: Casino) {
        casino.printAllResult { player ->
            println("${player.getString()} - 결과: ${player.totalScore}")
        }
    }

    private fun Participant.print() = println(getString())

    private fun Participant.getString(): String {
        return "${this.name}카드: ${this.myCards.getString()}"
    }

    private fun Cards.getString(): String {
        return getCardList().joinToString(", ") { card -> card.getString() }
    }

    private fun Card.getString(): String = getCardNumberString() + getCardSuitString()

    private fun Card.getCardNumberString(): String {
        return when (cardNumber) {
            CardNumber.Ace -> "A"
            CardNumber.Two -> "2"
            CardNumber.Three -> "3"
            CardNumber.Four -> "4"
            CardNumber.Five -> "5"
            CardNumber.Six -> "6"
            CardNumber.Seven -> "7"
            CardNumber.Eight -> "8"
            CardNumber.Nine -> "9"
            CardNumber.TEN -> "10"
            CardNumber.King -> "10"
            CardNumber.Queen -> "10"
            CardNumber.Jack -> "10"
        }
    }

    private fun Card.getCardSuitString(): String {
        return when (suit) {
            Suit.Spade -> "스페이드"
            Suit.Diamond -> "다이아몬드"
            Suit.Heart -> "하트"
            Suit.Clover -> "클로버"
        }
    }
}
