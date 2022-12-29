package blackjack.domain.card

enum class CardShape {
    CLOVER { override fun toString() = "클로버" },
    DIAMOND { override fun toString() = "다이아몬드" },
    HEART { override fun toString() = "하트" },
    SPADE { override fun toString() = "스페이드" }
    ;
}
