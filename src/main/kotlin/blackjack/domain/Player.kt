package blackjack.domain

interface Player {
    val name: String
    val deck: Deck

    fun addCard(card: Card)
    fun addCardAll(values: Collection<Card>)
    fun isAddable(): Boolean
    fun score(): Int
    fun currentDeck(): Deck
}
