package blackjack_dealer

import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.toGamerCards

object CardGenerator {
    fun generateSingleCard(cardDeque: CardDeque): Card {
        return cardDeque.cardDeque.removeLast()
    }

    fun generateDoubleCard(cardDeque: CardDeque): GamerCards {
        val initialFirstCard = generateSingleCard(cardDeque)
        val initialSecondCard = generateSingleCard(cardDeque)
        return listOf(initialFirstCard, initialSecondCard).toGamerCards()
    }
}
