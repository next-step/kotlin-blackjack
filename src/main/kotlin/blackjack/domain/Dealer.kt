package blackjack.domain

import blackjack.domain.card.Card

class Dealer {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator(GamerType.DEALER)

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
    }
}
