package blackjack

import blackjack.CardValue.Companion.CARD_Card_VALUES

data class Card (
    val pattern: Pattern,
    val cardValue: CardValue
) {
    override fun toString(): String {
        return "${cardValue.value}${pattern.description}"
    }

    fun received() {
        CARDS.remove(this)
    }

    companion object {
        fun getCard(): Card {
            return CARDS.first()
        }

        private val CARDS = CARD_Card_VALUES.flatMap { number -> Pattern.values().map { pattern -> Card(pattern, number) } }.shuffled().toMutableList()
    }
}

enum class Pattern(val description: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클로버")
}