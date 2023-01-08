package blackjack.ui

import blackjack.controller.Casino
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Suit

class ResultView {

    fun showPlayers(casino: Casino) {
        val names = casino.names()
        println("딜러와 ${names}에게 2장의 나누었습니다.")
        casino.dealer.print()
        repeat(casino.gamers.size) { index -> casino.gamers[index].print() }
    }

    fun showPlayer(player: Player) {
        if (player is Dealer) {
            showDealer()
            return
        }
        player.print()
    }

    fun showResult(casino: Casino) {
        println()
        showPlayerResult(casino.dealer)
        repeat(casino.gamers.size) { index -> showPlayerResult(casino.gamers[index]) }
    }

    private fun showPlayerResult(player: Player) {
        println("${player.getString()} - 결과: ${player.totalScore}")
    }

    fun showReport(casino: Casino) {
        println()
        println("## 최종 승패")

        val dealerReport = casino.dealer.makeReport(casino.gamers)
        println("딜러: ${dealerReport.winCount}승 ${dealerReport.loseCount}패")

        val others: List<Player> = casino.dealer + casino.gamers
        repeat(casino.gamers.size) action@{ index ->
            val gamer = casino.gamers[index]
            val report = gamer.makeReport(others.filter { it.name != gamer.name })

            val result = if (report.loseCount == 0 || casino.dealer.isBlackjack().not()) "승" else "패"
            println("${gamer.name}: $result")
        }
    }

    private fun showDealer() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private fun Player.print() = println(getString())

    private fun Player.getString(): String {
        if (this is Dealer) return "딜러: ${this.myCards.getString()}"
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
