package blackjack.model.strategy

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSuit

class NormalStrategy : ShuffleStrategy {
    override fun shuffle(): List<Card> {
        return CardNumber.values().flatMap { number ->
            CardSuit.values().map { suit ->
                Card(number, suit)
            }
        }
    }
}
