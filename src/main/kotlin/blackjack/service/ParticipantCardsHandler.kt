package blackjack.service

import blackjack.domain.Card

interface ParticipantCardsHandler {
    fun addCard(card: Card)
    fun canReceiveAdditionalCard(): Boolean
    fun getCards(): List<Card>
    fun getCardsString(): String
    fun getCardsResultPoint(): Int
}
