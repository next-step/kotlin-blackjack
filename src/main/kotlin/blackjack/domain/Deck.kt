package blackjack.domain

object Deck {
    val cards: MutableList<Card> = mutableListOf()

    init {
        Suits.values().forEach { suits ->
            Denomination.values().forEach { denomination ->
                cards.add(Card(suits, denomination))
            }
        }
        cards.shuffle()
    }

    fun shuffle() = cards.shuffled()
    fun take(number: Int): List<Card> {
        val card = cards.take(number)
        cards.removeAt(number)
        return card
    }
    fun take(): Card {
        val card = cards.first()
        cards.removeAt(0)
        return card
    }
}
