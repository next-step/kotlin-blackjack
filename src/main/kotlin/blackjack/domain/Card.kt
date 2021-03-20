package blackjack.domain

data class Card private constructor(
    val suit: CardSuit,
    val spell: CardSpell
) {
    override fun toString(): String {
        return "$spell$suit"
    }

    fun optimizeScore(total: Int): Int {
        if (spell.minorDigit != null && total + spell.minorDigit <= GameConfig.BUST_CONDITION) {
            return spell.minorDigit
        }

        return spell.digit
    }

    companion object {
        fun of(cardSuit: CardSuit, cardSpell: CardSpell): Card {
            return Card(cardSuit, cardSpell)
        }
    }
}
