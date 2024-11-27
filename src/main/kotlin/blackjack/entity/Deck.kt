package blackjack.entity

class Deck {
    val cards: List<Card> =
        Suit.entries.flatMap { suit ->
            Rank.entries.map { rank -> Card(suit, rank) }
        }.shuffled()

    private var dealtIndex = 0

    fun deal(): Card {
        require(dealtIndex < cards.size) { "덱에 남은 카드가 없습니다." }
        return cards[dealtIndex++]
    }

    fun remainCardCount(): Int = cards.size - dealtIndex
}
