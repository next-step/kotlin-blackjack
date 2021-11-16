package blackjack.domain

interface ParticipantCards {
    val cards: List<Card>
    fun getCardsString(): String
    fun add(card: Card)
}
