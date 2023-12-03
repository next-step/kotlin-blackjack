package blackjack.domain


class Deck(private val cards: MutableList<Card>) {
    fun getCard(): Card {
        return cards.removeFirst()
    }

    fun getCards(count: Int): List<Card> {
        val cardList = cards.take(count)
        cards.removeAll(cardList)
        return cardList
    }
}
