package blackjack.domain

interface Deck {
    fun sizeOfRemaining(): Int
    fun draw(): Card
}

class ShuffledDeck : Deck {
    private val cards: MutableList<Card> = Denomination.values()
        .flatMap { combine(it) }
        .shuffled()
        .toMutableList()

    private fun combine(denomination: Denomination): List<Card> {
        return Suit.values()
            .map { suit ->
                Card(denomination, suit)
            }
    }

    override fun sizeOfRemaining(): Int {
        return cards.size
    }

    override fun draw(): Card {
        check(cards.isNotEmpty()) { "뽑을 수 있는 카드가 없습니다" }
        return cards.removeFirst()
    }
}
