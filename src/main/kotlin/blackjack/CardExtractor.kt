package blackjack

import java.util.LinkedList

class CardExtractor {
    private val cards = LinkedList(allCards.shuffled())

    fun getCard(): Card {
        if (cards.isEmpty()) {
            cards.addAll(allCards.shuffled())
        }

        return cards.pop()
    }

    companion object {
        private val allCards: List<Card> = CardType.values().flatMap { cardType ->
            CardNumber.values().map { cardNumber ->
                Card(cardType, cardNumber)
            }
        }
    }
}
