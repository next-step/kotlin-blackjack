package blackjack_dealer.entity.card

enum class CardShape(val shapeName: String) {
    SPADE("스페이드"), HEART("하트"), CLOVER("클로버"), DIAMOND("다이아");

    companion object {
        fun findCardShapeFromName(name: String): blackjack.entity.CardShape {
            return blackjack.entity.CardShape.values().find { cardShape ->
                cardShape.shapeName == name
            } ?: error(UNEXPECTED_CARD_SHAPE)
        }

        private const val UNEXPECTED_CARD_SHAPE = "잘못 입력된 모양입니다."
    }
}
