package blackjack.ui

import blackjack.domain.*

class ResultView {

    private val dealer = Dealer()

    fun show(players: List<Player>) {
        introduceGamers(players)
        distribute(players)
        println()
        relay(players)
        showResult(players)
    }

    private fun introduceGamers(players: List<Player>) {
        val guide = players.joinToString(", ") { it.name }
        println("${guide}에게 2장의 나누었습니다.")
    }

    private fun distribute(players: List<Player>) {
        repeat(players.size) {
            val player = players[it]
            repeat(2) { player.receive(dealer.draw()) }
            printPlayer(player)
        }
    }

    private fun relay(players: List<Player>) {
        var index = 0
        do {
            val player = players[index]
            val next = ask(player)

            if (player.canDraw().not()) break

            if (next) index++
        } while (index < players.size)
    }

    private fun ask(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readLine()
        if (answer.isNullOrBlank()) {
            return true
        }

        if (answer == "n") {
            return true
        }

        player.receive(dealer.draw())

        if (player.canDraw().not()) return true

        printPlayer(player)

        return ask(player)
    }

    private fun showResult(players: List<Player>) {
        repeat(players.size) {
            val gamer = players[it]
            println("$gamer - 결과: ${gamer.totalScore}")
        }
    }

    private fun printPlayer(player: Player) {
        println("${player.name}카드: ${printCards(player.myCards)}")
    }

    private fun printCards(cards: Cards): String {
        return cards.getCardList().joinToString(", ") { card ->
            getCardString(card)
        }
    }

    private fun getCardString(card: Card): String {
        return getCardNumberString(card) + getCardSuitString(card)
    }

    private fun getCardNumberString(card: Card): String {
        return when (card.cardNumber) {
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

    private fun getCardSuitString(card: Card): String {
        return when (card.suit) {
            Suit.Spade -> "스페이드"
            Suit.Diamond -> "다이아몬드"
            Suit.Heart -> "하트"
            Suit.Clover -> "클로버"
        }
    }
}
