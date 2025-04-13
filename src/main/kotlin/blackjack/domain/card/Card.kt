package blackjack.domain.card

data class Card(
    private val number: CardNumber,
    private val suit: Suit,
) {
    constructor(rawNumber: String, rawSuit: String) : this(CardNumber.fromName(rawNumber), Suit.fromName(rawSuit))
}
