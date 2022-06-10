package blackjack.domain.interfaces

import blackjack.domain.Card
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape

class RandomCardFactory : CardFactory {
    override fun create(): Card {
        return Card.of(CardShape.randomShape(), CardPoint.randomPoint())
    }
}
