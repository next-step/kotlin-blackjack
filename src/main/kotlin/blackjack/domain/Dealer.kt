package blackjack.domain

import blackjack.domain.card.Card

class Dealer : BlackJackGamer {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator(GamerType.DEALER)

    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
    }

    override fun getGamerType(): GamerType {
        return GamerType.DEALER
    }
}
