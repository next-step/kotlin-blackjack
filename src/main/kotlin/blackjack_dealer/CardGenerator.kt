package blackjack_dealer

import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque

object CardGenerator {
    fun generateSingleCard(): Card {
        return CardDeque.cardDeque.removeLast()
    }
}
