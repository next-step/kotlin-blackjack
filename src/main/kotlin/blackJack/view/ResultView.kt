package blackJack.view

import blackJack.domain.BlackJack
import blackJack.domain.Dealer
import blackJack.domain.Person
import blackJack.domain.WinOrLose

object ResultView {
    fun blank() {
        println()
    }

    fun resultError(error: String?) {
        println(error)
    }

    fun resultReady(blackJack: BlackJack) {
        val players = blackJack.players
        val dealer = blackJack.dealer
        println("딜러가 딜러와 ${players.joinToString { it.name }}에게 2장의 카드를 주었습니다.")
        println("딜러 카드: ${dealer.hands[0]}")
        blank()
        players.forEach { resultPeopleHands(it) }
    }

    fun resultPeopleHands(person: Person, result: String = "") {
        println("$person 카드: ${person.hands.joinToString { it.toString() }} $result")
        blank()
    }

    fun resultWhetherBust(person: Person) {
        if (person.isBust()) {
            resultPeopleHands(person)
            println("${person}는 버스트 했습니다")
        } else {
            resultPeopleHands(person)
            println("${person}는 버스트 하지 않았습니다.")
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
        players.forEach { resultPeopleHands(it, "- 결과: ${it.getTotalScore()}") }
        resultPeopleHands(dealer, "- 결과: ${dealer.getTotalScore()}")
        blank()
        println("최종 승패")
        val result = blackJack.getResult()
        players.forEach { println("$it: ${result.playersResult.getValue(it).string}") }
        println("딜러: ${result.dealerResult[WinOrLose.WIN]}승 ${result.dealerResult[WinOrLose.DRAW]}무 ${result.dealerResult[WinOrLose.LOSE]}패")
    }
}
