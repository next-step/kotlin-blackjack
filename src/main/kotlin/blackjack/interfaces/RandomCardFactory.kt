package blackjack.interfaces

import blackjack.Card
import blackjack.enums.CardPoint
import blackjack.enums.CardShape

class RandomCardFactory : CardFactory {
    override fun create(): Card {
        return Card.of(CardShape.randomShape(), CardPoint.randomPoint())
    }
}
