package blackjack.domain.strategy

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.util.RandomNumberGenerator

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
