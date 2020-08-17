package blackjack.domain

class Deck(private val cards: List<Card>) {
    private var count = 0

    fun draw() = cards[count++]

    companion object {
        fun of(packs: List<List<Card>>): Deck {
            return Deck(packs.flatten().shuffled())
        }
    }
}
