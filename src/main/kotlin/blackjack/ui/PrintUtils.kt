package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Participant
import blackjack.entity.CardNumber
import blackjack.entity.ParticipantCards

fun Card.print(): String {
    val cardNumber = takeCardNumber()
    return "${cardNumber}${shape.shapeName}"
}

private fun Card.takeCardNumber() =
    number.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
        ?: useNumberIfTen()

private fun Card.useNumberIfTen() = if (number == CardNumber.TEN) number.number else number.name

fun ParticipantCards.print(): String {
    return cards.joinToString { card -> card.print() }
}

fun Participant.print(): String {
    val cardString = participantCards.print()
    val resultString = "결과: ${participantCards.getCurrentScore()}"
    return "$name 카드: $cardString - $resultString"
}

private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9
