package blackjack

import blackjack.CardValue.Companion.CARD_VALUES

data class Card (
    val pattern: Pattern,
    val cardValue: CardValue
) {
    override fun toString(): String {
        return "${cardValue.value}${pattern.description}"
    }

    companion object {
        fun getCard(): Card {
            return CARDS.removeFirst()
        }

        val CARDS = CARD_VALUES.flatMap { number -> Pattern.values().map { pattern -> Card(pattern, number) } }.shuffled().toMutableList()
    }
}

enum class Pattern(val description: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버")
}