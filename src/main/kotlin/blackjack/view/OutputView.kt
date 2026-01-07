package blackjack.view

import blackjack.domain.DEALER_ADD_CARD_BASE_SCORE
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player

object OutputView {
    fun initialDeal(playerNames: List<String>) = println("딜러와 ${playerNames.joinToString { it }}에게 2장을 나누었습니다.")

    fun dealerInitialCardList(player: Dealer) = println("${player.name}카드: ${player.cards.take(1).joinToString { it.toString() }}")

    fun dealerAdditionalCondition() = println("딜러는 ${DEALER_ADD_CARD_BASE_SCORE}이하라 한장의 카드를 더 받았습니다.")

    fun cardList(participant: Participant) = println(createCardListMessage(participant))

    fun scoreResult(participant: Participant) = println("${createCardListMessage(participant)} - 결과: ${participant.totalScore()}")

    fun income() = println("## 최종 수익")

    fun dealerIncome(dealerMoney: Int) {
        println("딜러 : $dealerMoney")
    }

    fun playersIncomes(players: List<Player>) {
        players.forEach { player -> println("${player.name}: ${player.income}") }
    }

    private fun createCardListMessage(participant: Participant) =
        "${participant.name}카드: ${participant.cards.joinToString { it.toString() }}"
}
