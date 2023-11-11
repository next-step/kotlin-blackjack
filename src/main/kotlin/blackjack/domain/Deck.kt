package blackjack.domain

class Deck {
    private val cards = ArrayList<Card>(setupDeck())

    fun draw(): Card {
        check(cards.isNotEmpty()) {
            "덱에 남아있는 카드가 없습니다."
        }

        return cards.removeFirst()
    }

    private fun setupDeck(): List<Card> {
        return Suit.values().flatMap { suit ->
            Rank.values().map { rank ->
                Card(suit, rank)
            }
        }
    }
}
