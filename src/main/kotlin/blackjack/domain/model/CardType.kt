package blackjack.domain.model

enum class CardType {
    SPADE,
    CLOVER,
    DIAMOND,
    HEART;

    companion object {
        fun CardType.toText(): String {
            return when (this) {
                SPADE -> "스페이드"
                CLOVER -> "클로버"
                DIAMOND -> "다이아몬드"
                HEART -> "하트"
            }
        }
    }
}
