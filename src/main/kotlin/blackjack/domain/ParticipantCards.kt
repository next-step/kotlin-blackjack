package blackjack.domain

interface ParticipantCards {
    val cards: List<Card>
    fun getCardsString(): String
    fun add(card: Card)
    fun getSumOfCardsPoint(): Int = cards.sumOf { CardPoint.getPoint(it.number.rank).point }
    fun hasAceCard(): Boolean = cards.any { card -> card.hasAce() }
}
