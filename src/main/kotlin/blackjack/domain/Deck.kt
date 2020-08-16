package blackjack.domain

class Deck(val cards: MutableList<Card> = mutableListOf()) {
    init {
        Suits.values().forEach { suits ->
            Denomination.values().forEach { denomination ->
                cards.add(Card(suits, denomination))
            }
        }
    }

    fun shuffle() = cards.shuffled()
    fun take(number: Int) = cards.take(number)
}
