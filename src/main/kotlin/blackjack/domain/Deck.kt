package blackjack.domain

class Deck(deck: List<Card>) : List<Card> by deck {
    private val shuffled: MutableList<Card>
        get() = shuffled().toMutableList()

    @OptIn(ExperimentalStdlibApi::class)
    fun next(): Card {
        return shuffled.removeFirst()
    }
}
