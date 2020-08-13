package blackJack.view

import blackJack.domain.BlackJack
import blackJack.domain.Dealer
import blackJack.domain.People

object ResultView {
    fun resultReady(blackJack: BlackJack) {
        val players = blackJack.players
        println("딜러가 딜러와 ${players.joinToString { it.name!! }}에게 2장의 카드를 주었습니다.")
        println("딜러 카드: ${blackJack.dealer.hands[0].name}")
        players.forEach { resultPeopleHands(it) }
        println()
    }

    fun resultPeopleHands(people: People, result: String = "") {
        println("${people.name} 카드: ${people.hands.joinToString { it.name }} $result")
    }

    fun resultWhetherBust(people: People) {
        if (people.isBust()) {
            resultPeopleHands(people)
            println("${people.name}는 버스트 했습니다")
            println()
        } else {
            resultPeopleHands(people)
            println("${people.name}는 버스트 하지 않았습니다.")
        }
    }

    fun resultOpenDealerCard(dealer: Dealer) {
        println("딜러의 카드를 오픈합니다")
        resultPeopleHands(dealer)
    }

    fun resultDealerGetCard(dealer: Dealer) {
        println("딜러의 카드 총합이 16이하라 카드를 하나 더 받았습니다.")
        resultWhetherBust(dealer)
    }

    fun resultGame(blackJack: BlackJack) {
        println("----------------")
        blackJack.players.forEach { resultPeopleHands(it, "- 결과: ${it.getTotalScore()}") }
        resultPeopleHands(blackJack.dealer, "- 결과: ${blackJack.dealer.getTotalScore()}")
        println()
        println("최종 승패")
        val result = blackJack.getResult()
        blackJack.players.forEach { println("${it.name}: ${result.get(it)}") }
        println("딜러: ${result.dealerResult["승"]}승 ${result.dealerResult["무"]}무 ${result.dealerResult["패"]}패")
    }
}
