package blackjack.interfaces

import blackjack.Card
import blackjack.enums.CardPoint
import blackjack.enums.CardShape

class FixedCardFactory(private val shape: CardShape, private val point: CardPoint) : CardFactory {
    override fun create(): Card {
        return Card.of(shape, point)
    }
}
