package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CompareResult
import blackjack.domain.Number
import blackjack.domain.Participant
import blackjack.domain.Shape

object OutputView {

    fun printParticipantOpenedCards(openCards: Map<String, List<Card>>) {
        val names = openCards.keys.joinToString(separator = ", ")
        println("${names}에게 2장의 카드를 나누었습니다")
        openCards.entries.forEach {
            printParticipantCards(it.key, it.value)
        }
    }

    fun printParticipantCards(name: String, cards: List<Card>) {
        val cardsText = cards.joinToString(", ") { cardText(it) }
        println("${name} 카드 : $cardsText")
    }

    private fun cardText(card: Card): String {
        return numberText(card.number) + shapeText(card.shape)
    }

    private fun numberText(number: Number): String {
        return when(number) {
            Number.ACE -> "A"
            Number.JACK -> "J"
            Number.QUEEN -> "Q"
            Number.KING -> "K"
            else -> number.value.toString()
        }
    }

    private fun shapeText(shape: Shape): String {
        return when(shape) {
            Shape.DIAMOND -> "다이아몬드"
            Shape.HEART -> "하트"
            Shape.SPADE -> "스페이드"
            Shape.CLOVER -> "클로버"
        }
    }

    fun printObtainDealerCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printParticipantHands(participants: List<Participant>) {
        for (participant in participants) {
            val hands = participant.hands.joinToString(", ") { cardText(it) }
            println("${participant.name} 카드 : $hands - 결과: ${participant.sumOfCards()}")
        }
    }

    fun printCompareResults(compareResults: Map<String, CompareResult>) {
        println("## 최종 승패")
        printDealerResult(compareResults)
        printPlayerResult(compareResults)
    }

    private fun printDealerResult(compareResults: Map<String, CompareResult>) {
        val dealerWinCount = compareResults.values.count { it == CompareResult.DEALER_WIN }
        val dealerDrawCount = compareResults.values.count { it == CompareResult.DRAW }
        val dealerLoseCount = compareResults.values.count { it == CompareResult.DEALER_LOSE }
        println("딜러 : ${dealerWinCount}승 ${dealerDrawCount}무 ${dealerLoseCount}패")
    }

    private fun printPlayerResult(compareResults: Map<String, CompareResult>) {
        compareResults.forEach { (name, result) ->
            println("${name} ${playerResultText(result)}")
        }
    }

    private fun playerResultText(result: CompareResult): String {
        return when(result) {
            CompareResult.DEALER_LOSE -> "승"
            CompareResult.DRAW -> "무"
            CompareResult.DEALER_WIN -> "패"
        }
    }
}
