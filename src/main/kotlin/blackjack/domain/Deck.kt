package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit

class Deck(
    private val cards: MutableList<Card> = createShuffledDeck(),
) {

    fun draw(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("덱의 카드들이 모두 사용되었습니다. 다른 덱을 사용해주세요.")
        }

        return cards.removeAt(0)
    }

    companion object {
        private fun createShuffledDeck(): MutableList<Card> {
            return CardSuit.all.flatMap { suit ->
                CardRank.all.map { rank ->
                    Card(suit, rank)
                }
            }.shuffled().toMutableList()
        }
    }

}
