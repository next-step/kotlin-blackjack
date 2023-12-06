package blackjack.domain

class Cards(
    val values: List<Card> = emptyList()
) {
    val size
        get() = values.size

    override fun toString(): String {
        return values.joinToString(",  ")
    }

    operator fun plus(card: Card): Cards {
        return Cards(values + card)
    }
}

fun List<Card>.toCards(): Cards {
    return Cards(this)
}
