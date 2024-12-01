package blackjack.entity

class Deck {
    val cards: MutableList<Card> =
        Suit.entries.flatMap { suit ->
            Rank.entries.map { rank -> Card(suit, rank) }
        }.shuffled()
            .toMutableList()

    fun deal(): Card {
        require(cards.isNotEmpty()) { "덱에 남은 카드가 없습니다." }
        return cards.removeFirst()
    }

    fun remainCardCount(): Int = cards.size
}
