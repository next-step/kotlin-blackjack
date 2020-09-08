package blackJack.view

import blackJack.domain.BlackJack
import blackJack.domain.Dealer
import blackJack.domain.Person

object ResultView {
    private fun blank() {
        println()
    }

    fun resultError(error: String?) {
        println(error)
    }

    fun resultReady(blackJack: BlackJack) {
        val players = blackJack.players
        val dealer = blackJack.dealer
        println("딜러가 딜러와 ${players.players.joinToString { it.name }}에게 2장의 카드를 주었습니다.")
        println("딜러 카드: ${dealer.hands.getFirstCard()}")
        blank()
        players.players.forEach { resultPeopleHands(it) }
    }

    private fun resultPeopleHands(person: Person, result: String = "") {
        println("${person.name} 카드: ${person.hands.cards.joinToString { it.toString() }} $result")
        blank()
    }

    fun resultWhetherBust(person: Person) {
        if (person.isBust()) {
            resultPeopleHands(person)
            println("${person.name}는 버스트 했습니다")
        } else {
            resultPeopleHands(person)
            println("${person.name}는 버스트 하지 않았습니다.")
        }
        blank()
    }

    fun resultOpenDealerCard(dealer: Dealer) {
        println("딜러의 카드를 오픈합니다")
        resultPeopleHands(dealer)
    }

    fun resultDealerGetCard(dealer: Dealer) {
        println("딜러의 카드 총합이 16이하라 카드를 더 받았습니다.")
        resultWhetherBust(dealer)
    }

    fun resultGame(blackJack: BlackJack) {
        val players = blackJack.players
        val dealer = blackJack.dealer
        println("----------------")
        players.players.forEach { resultPeopleHands(it, "- 결과: ${it.getTotalScore()}") }
        resultPeopleHands(dealer, "- 결과: ${dealer.getTotalScore()}")
        blank()
        println("최종 수익")
        val result = blackJack.getResult()
        players.players.forEach { println("${it.name}: ${result.playersProfit.getValue(it)}") }
        println("딜러: ${result.dealerProfit}")
    }
}
