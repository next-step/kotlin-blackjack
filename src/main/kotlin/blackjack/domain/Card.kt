package blackjack.domain

class Card private constructor(
    val number: CardNumber,
    val pattern: CardPattern
) {

    companion object {
        val CARDS = CardPattern.values().associateWith { pattern ->
            CardNumber.values().map { number -> Card(number, pattern) }
        }.values.flatten()
    }
}
