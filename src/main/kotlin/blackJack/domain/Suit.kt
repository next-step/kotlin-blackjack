package blackJack.domain

enum class Suit {
    SPADE,
    HEART,
    DIAMOND,
    CLUB,
    ;

    companion object {
        fun randomizeSuit(): Suit {
            return values().random()
        }
    }
}
