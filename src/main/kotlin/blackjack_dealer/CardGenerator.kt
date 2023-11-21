package blackjack_dealer

import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque

object CardGenerator {
    fun generateSingleCard(cardDeque: CardDeque): Card {
        return cardDeque.cardDeque.removeLast()
    }
}
