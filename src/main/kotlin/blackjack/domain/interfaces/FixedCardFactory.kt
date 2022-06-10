package blackjack.domain.interfaces

import blackjack.domain.Card
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape

class FixedCardFactory(private val shape: CardShape, private val point: CardPoint) : CardFactory {
    override fun create(): Card {
        return Card.of(shape, point)
    }
}
