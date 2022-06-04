package blackjack.domain.card

data class Card(
    val pattern: CardPattern,
    val cardType: CardType
)

enum class CardPattern {
    SPADE,
    HEART,
    DIAMOND,
    CLOVER
}
