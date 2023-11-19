package blackjack.model.card

import blackjack.model.strategy.ShuffleStrategy

class CardDeck(shuffleStrategy: ShuffleStrategy) {
    private var deck: MutableList<Card> = shuffleStrategy.shuffle(initializedDeck()).toMutableList()

    fun draw(): Card {
        check(deck.isNotEmpty()) { NOT_EXIST_CARD_EXCEPTION }
        return deck.removeFirst()
    }

    private fun initializedDeck(): List<Card> {
        return CardNumber.values().flatMap { number ->
            CardSuit.values().map { suit ->
                Card(number, suit)
            }
        }
    }

    companion object {
        private const val NOT_EXIST_CARD_EXCEPTION = "더 이상 카드가 존재하지 않습니다."
    }
}
