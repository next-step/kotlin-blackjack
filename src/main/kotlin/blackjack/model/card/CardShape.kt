package blackjack.model.card

enum class CardShape {
    SPADES,
    DIAMONDS,
    HEARTS,
    CLUBS;

    companion object {
        val count: Int = CardShape.values().size
    }
}
