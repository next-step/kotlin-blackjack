package blackjack.domain

interface ParticipantCardsHandler {
    fun addCard(card: Card)
    fun canReceiveAdditionalCard(): Boolean
    fun getCards(): List<Card>
    fun getCardsString(): String
    fun getCardsResultPoint(): Int
}
