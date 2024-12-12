package blackjack.domain

fun interface Deck {
    fun draw(): Card
}

class CardDeck(
    suits: List<Suit> = Suit.entries,
    private val ranks: List<Rank> = Rank.entries,
) : Deck {
    // Card 상태 변경
    val cards: MutableList<Card> =
        suits.flatMap { suit -> // Suit -> Rank 쌍 만들기
            ranks.map { rank -> Card(rank, suit) }
        }.toMutableList()

    override fun draw(): Card {
        check(cards.isNotEmpty()) { "No more cards" }
        cards.shuffle()
        return cards.removeAt(0)
    }
}
