package blackjack

import kotlin.random.Random

class CardExtractor {

    fun getCard(): Card {
        return allCards.removeAt(Random.nextInt(allCards.size))
    }

    companion object {
        private val allCards: MutableList<Card> = CardType.values().flatMap { cardType ->
            CardNumber.values().map { cardNumber ->
                Card(cardType, cardNumber)
            }
        }.toMutableList()
    }
}
