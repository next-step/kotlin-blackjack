package blackjack.domain

interface Participant {
    val name: String
    val hand: Hand
    val state: State

    fun receive(card: Card)
}

enum class State(
    val canPlay: Boolean
) {
    HITTABLE(true),
    STAY(false),
    BUST(false),
    BLACKJACK(false),
}
