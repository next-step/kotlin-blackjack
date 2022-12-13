package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.Casino
import blackjack.domain.Player
import blackjack.domain.Suit

class ResultView {

    fun show(casino: Casino) {
        casino.distribute()

        val names = casino.players.joinToString(", ") { it.name }
        println("${names}에게 2장의 나누었습니다.")

        repeat(casino.players.size) {
            casino.players[it].print()
        }

        relay(casino)

        showResult(casino)
    }

    private fun relay(casino: Casino) {
        var index = 0
        do {
            val player = casino.players[index]
            val next = ask(casino, player)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < casino.players.size)
    }

    private fun ask(casino: Casino, player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readlnOrNull()
        if (answer.isNullOrBlank()) return true
        if (answer == NO) return true

        casino.draw(player)

        if (player.canDraw().not()) return true

        player.print()

        return ask(casino, player)
    }

    private fun showResult(casino: Casino) {
        repeat(casino.players.size) {
            val player = casino.players[it]
            println("${player.getString()} - 결과: ${player.totalScore}")
        }
    }

    private fun Player.print() = println(getString())

    private fun Player.getString(): String {
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

    companion object {
        private const val NO = "n"
    }
}
