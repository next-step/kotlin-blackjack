package blackjack.domain

interface Deck {
    fun fillDeck(newCards: List<Card>)

    fun drawCard(): Card
}
