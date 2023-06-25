package blackjack.domain.card

enum class CardShape {
    SPADE,
    HEART,
    DIAMOND,
    CLOVER,
    ;

    companion object {

        val ALL_CARD_SHAPES = CardShape.values()
    }
}
