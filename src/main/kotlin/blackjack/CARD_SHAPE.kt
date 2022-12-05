package blackjack

enum class CARD_SHAPE {
    Spade, Hart, Diamond, Clover;

    companion object {
        fun of(): CARD_SHAPE = values().random()
    }
}
