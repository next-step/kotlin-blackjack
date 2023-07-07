package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.result.Result

object OutputView {
    fun printStart(participants: List<Participant>) {
        val playerNames = participants.joinToString(", ") { it.name }
        println("$playerNames 에게 2장의 카드를 나누었습니다.")
        for (participant in participants) {
            printCards(participant.name, participant.cards())
        }
    }

    fun printCards(name: String, cards: List<Card>) {
        println(resultMessage(name, cards))
    }

    fun printDealerHit(dealer: Dealer) {
        println("${dealer.name}은 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printPlayerResult(participant: Participant) {
        println("${resultMessage(participant.name, participant.cards())}- 결과: ${participant.score().value}")
    }

    private fun resultMessage(name: String, cards: List<Card>): String {
        val cardsMessage = cards.joinToString(", ") { it.number.displayName() + it.shape.displayName() }
        return "${name}의 카드: $cardsMessage"
    }

    fun printResult(name: String, result: Result) {
        println("$name: ${result.displayName()}")
    }

    fun printResult(name: String, results: Map<Result, Int>) {
        val message = results.map { (result, count) -> count.toString() + result.displayName() }.joinToString(" ")
        println("$name: $message")
    }
}

private fun CardNumber.displayName() = when (this) {
    CardNumber.ACE -> "A"
    CardNumber.JACK -> "J"
    CardNumber.QUEEN -> "Q"
    CardNumber.KING -> "K"
    else -> this.cardScore.primary.toString()
}

private fun CardShape.displayName() = when (this) {
    CardShape.CLUB -> "클로버"
    CardShape.DIAMOND -> "다이아"
    CardShape.HEART -> "하트"
    CardShape.SPADE -> "스페이드"
}

private fun Result.displayName() = when (this) {
    Result.WIN -> "승"
    Result.DRAW -> "무"
    Result.LOSE -> "패"
}
