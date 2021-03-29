package blackjack

import java.util.LinkedList

abstract class CardExtractor(protected val cards: LinkedList<Card>) {

    fun getCard(): Card {
        if (cards.isEmpty()) {
            addAll()
        }

        return cards.pop()
    }

    abstract fun addAll()
}

class RandomCardExtractor : CardExtractor(LinkedList(allCards.shuffled())) {

    override fun addAll() {
        cards.addAll(allCards.shuffled())
    }

    companion object {
        private val allCards: List<Card> = CardType.values().flatMap { cardType ->
            CardNumber.values().map { cardNumber ->
                Card(cardType, cardNumber)
            }
        }
    }
}
