package blackjack.ui

import blackjack.entity.Card
import blackjack.entity.Cards
import blackjack.entity.Participant

fun Card.print(): String {
    val cardNumber = number.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
        ?: number.name
    return "${cardNumber}${shape.shapeName}"
}

fun Cards.print(): String {
    return cards.joinToString { card -> card.print() }
}

fun Participant.print(): String {
    val cardString = cards.print()
    val resultString = "결과: ${sumOfCardNumbers()}"
    return "$name 카드: $cardString - $resultString"
}

private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9
