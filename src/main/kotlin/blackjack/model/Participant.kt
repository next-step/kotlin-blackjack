package blackjack.model

data class Participant(
    val name: String,
    val cards: MutableList<Card> = mutableListOf(),
)
