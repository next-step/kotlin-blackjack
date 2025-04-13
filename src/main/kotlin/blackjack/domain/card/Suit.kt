package blackjack.domain.card

enum class Suit {
    SPADES,
    HEARTS,
    DIAMONDS,
    CLUBS,
    ;

    companion object {
        fun fromName(name: String) =
            entries.find { it.name == name.uppercase() }
                ?: throw IllegalArgumentException("Card suit does not exist for $name")
    }
}
