package blackjack.domain

interface ParticipantCards {
    val cards: List<Card>
    override fun toString(): String
    fun add(card: Card)
}
