package blackjack.view

import blackjack.domain.DEALER_ADD_CARD_BASE_SCORE
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.RecordType

object OutputView {
    fun initialDeal(playerNames: List<String>) = println("딜러와 ${playerNames.joinToString { it }}에게 2장을 나누었습니다.")

    fun dealerInitialCardList(player: Dealer) = println("${player.name}카드: ${player.cards.take(1).joinToString { it.toString() }}")

    fun dealerAdditionalCondition() = println("딜러는 ${DEALER_ADD_CARD_BASE_SCORE}이하라 한장의 카드를 더 받았습니다.")

    fun cardList(participant: Participant) = println(createCardListMessage(participant))

    fun scoreResult(participant: Participant) = println("${createCardListMessage(participant)} - 결과: ${participant.totalScore()}")

    fun winOrLoseTitle() = println("## 최종 승패")

    fun dealerRecords(recordType: List<RecordType>) {
        val (winCount, drawCount, loseCount) =
            recordType.fold(Triple(0, 0, 0)) { acc, type ->
                when (type) {
                    RecordType.WIN -> Triple(acc.first + 1, acc.second, acc.third)
                    RecordType.DRAW -> Triple(acc.first, acc.second + 1, acc.third)
                    RecordType.LOSE -> Triple(acc.first, acc.second, acc.third + 1)
                }
            }

        println("딜러 : ${winCount}승 ${drawCount}무 ${loseCount}패")
    }

    fun playersRecords(matchResults: Map<String, RecordType>) {
        matchResults.forEach { (name, recordType) -> println("$name : ${recordType.displayName}") }
    }

    private fun createCardListMessage(participant: Participant) =
        "${participant.name}카드: ${participant.cards.joinToString { it.toString() }}"
}
