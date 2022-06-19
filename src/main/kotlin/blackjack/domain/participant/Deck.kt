package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

interface Deck {
    fun isEmpty(): Boolean
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

    override fun isEmpty(): Boolean {
        return cards.isEmpty()
    }

    override fun draw(): Card {
        check(cards.isNotEmpty()) { "뽑을 수 있는 카드가 없습니다" }
        return cards.removeFirst()
    }
}
