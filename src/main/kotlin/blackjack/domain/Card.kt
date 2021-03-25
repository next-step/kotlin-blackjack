package blackjack.domain

data class Card private constructor(
    val suit: CardSuit,
    val spell: CardSpell
) {
    fun getDigit(): Int {
        return spell.digit
    }

    companion object {
        fun of(cardSuit: CardSuit, cardSpell: CardSpell): Card {
            return Card(cardSuit, cardSpell)
        }
    }
}
