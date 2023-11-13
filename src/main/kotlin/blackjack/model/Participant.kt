package blackjack.model

data class Participant(
    val name: String,
    val cards: List<Card>? = null,
)
