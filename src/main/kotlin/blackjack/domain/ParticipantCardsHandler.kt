package blackjack.domain

interface ParticipantCardsHandler {
    fun addCard(card: Card)
    fun canReceiveAdditionalCard(): Boolean
    fun getCards(): ParticipantCards
    fun getCardsString(): String
}
