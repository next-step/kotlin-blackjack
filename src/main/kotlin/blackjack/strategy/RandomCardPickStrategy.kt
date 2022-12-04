package blackjack.strategy

import blackjack.Card
import blackjack.CardNumber
import blackjack.CardShape
import blackjack.util.RandomNumberGenerator

class RandomCardPickStrategy : CardPickStrategy {
    override fun pick(): Card {
        val cardShape = CardShape.values()[getCardShapeIdx()]
        val cardNumber = CardNumber.values()[getCardNumberIdx()]
        return Card(cardShape, cardNumber)
    }

    private fun getCardShapeIdx(): Int {
        val cardShapeSize = CardShape.values().size - 1
        return RandomNumberGenerator.generate(0..cardShapeSize)
    }

    private fun getCardNumberIdx(): Int {
        val cardNumberSize = CardNumber.values().size - 1
        return RandomNumberGenerator.generate(0..cardNumberSize)
    }
}
