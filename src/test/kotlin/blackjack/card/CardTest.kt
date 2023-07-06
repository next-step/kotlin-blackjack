package blackjack.card

enum class Pattern {
    CLOVER,
    DIAMOND,
    HEART,
    SPADE,
}

data class Card(val number: String, val pattern: Pattern)
class CardTest
