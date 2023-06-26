package blackjack.domain

class Cards(private val cards: List<Card>) {

    fun all(): List<Card> = cards.toList()

    fun addCard(card: Card): Cards {
        return Cards(cards + listOf(card))
    }
}
