package blackjack.domain

interface ParticipantCards {
    val cards: List<Card>
    fun getCardsString(): String
    fun add(card: Card)

    companion object {
        const val MAXIMUM_SUM_OF_CARD_NUMBERS = 21
    }
}
