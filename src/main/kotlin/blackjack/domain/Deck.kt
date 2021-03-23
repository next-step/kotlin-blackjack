package blackjack.domain

class Deck(deck: List<Card>) {
    private val shuffled: MutableList<Card> = deck.shuffled().toMutableList()

    @OptIn(ExperimentalStdlibApi::class)
    fun next(): Card {
        return shuffled.removeFirst()
    }
}
