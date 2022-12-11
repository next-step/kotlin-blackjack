package blackjack.domain.card

enum class Suit {
    SPADES, HEARTS, DIAMONDS, CLUBS;

    override fun toString(): String {
        return when (this) {
            SPADES -> "스페이드"
            HEARTS -> "하트"
            DIAMONDS -> "다이아몬드"
            CLUBS -> "클럽"
        }
    }
}
