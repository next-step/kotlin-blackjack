package blackjack.domain

interface Player {
    val name: String
    val deck: Deck

    fun addCard(card: Card)
    fun isAddable(): Boolean
    fun score(): Int
}
