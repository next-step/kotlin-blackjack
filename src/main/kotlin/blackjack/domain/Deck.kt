package blackjack.domain

class Deck {
    private val cards: MutableList<Card> = initializeCardsByCardSuit()

    fun pick(): Card {
        if (isEmpty()) throw IllegalStateException("덱에 카드가 더이상 존재하지 않습니다.")
        val card: Card = cards.random()
        cards.remove(card)
        return card
    }

    private fun isEmpty(): Boolean {
        return cards.isEmpty()
    }

    private fun initializeCardsByCardSuit(): MutableList<Card> {
        val cardsSet: MutableList<Card> = mutableListOf()
        CardSuit.values().forEach {
            initializeCardsByCardSpell(cardsSet, it)
        }
        return cardsSet
    }

    private fun initializeCardsByCardSpell(cardsSet: MutableList<Card>, cardSuit: CardSuit) {
        CardSpell.values().forEach {
            cardsSet.add(Card.of(cardSuit, it))
        }
    }
}
