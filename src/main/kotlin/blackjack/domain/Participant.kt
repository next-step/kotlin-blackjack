package blackjack.domain

interface Participant {
    val name: String
    val hand: Hand
    val state: State

    fun saidHit(): Boolean
    fun receive(card: Card)
    fun open(): Hand = hand
}

enum class State(
    val canPlay: Boolean
) {
    HITTABLE(true),
    STAY(false),
    BUST(false),
    BLACKJACK(false);
}
