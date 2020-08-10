package blackjack.domain.player

import blackjack.domain.deck.Card

data class Player(private val id: Int, private val name: String) {
    private val hand = Hand()
    private var status: HandStatus = HandStatus.GENERAL

    override fun toString(): String = "$name : ${getCards().joinToString()}"

    fun giveCards(card: Card) {
        if (status != HandStatus.GENERAL) {
            return
        }
        hand.addNew(card)
    }

    fun getCards() = hand.getCards()
}
