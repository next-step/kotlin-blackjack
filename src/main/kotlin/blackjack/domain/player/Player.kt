package blackjack.domain.player

import blackjack.domain.deck.Card

data class Player(
    private val id: Int,
    val name: String
) : Participant {
    override val hand = Hand()

    fun hasFreeSpace() = hand.hasFreeSpace()

    override fun toString(): String = "$name : ${getCards().joinToString()}"

    override fun giveCard(card: Card): HandStatus {
        return hand.addNew(card)
    }

    override fun getCards() = hand.getCards()
}
