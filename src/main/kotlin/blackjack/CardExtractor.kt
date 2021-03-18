package blackjack

import java.util.Random

class CardExtractor {
    private val allCards: MutableList<Card> = CardType.values().flatMap { cardType ->
        CardNumber.values().map { cardNumber ->
            Card(cardType, cardNumber)
        }
    }.toMutableList()
    private val random = Random()

    fun getCard(): Card {
        return allCards.removeAt(random.nextInt(allCards.size))
    }
}
