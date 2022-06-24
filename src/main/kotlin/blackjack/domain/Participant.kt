package blackjack.domain

sealed class Participant(
    val name: String = "",
    cards: List<Card> = emptyList(),
) {
    abstract fun canReceive(): Boolean

    fun score(): Score = Score(0)
}
