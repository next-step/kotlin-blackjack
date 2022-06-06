package blackjack.domain.card

data class Card(
    val pattern: CardPattern,
    val type: CardType
)

enum class CardPattern(val description: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버")
}
