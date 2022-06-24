package blackjack.domain

sealed class Participant(
    val name: String = "",
    cards: List<Card> = emptyList(),
) {
    abstract fun canReceive(): Boolean
}
