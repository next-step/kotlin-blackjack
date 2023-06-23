package blackjack.domain

sealed interface Player {
    val name: String
    val deck: Deck

    fun addCard(card: Card)
    fun addCardAll(values: Collection<Card>)
    fun isAddable(): Boolean
    fun calculateScore(): Int
    fun currentDeck(): Deck
}
