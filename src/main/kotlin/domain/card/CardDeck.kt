package domain.card

import domain.factory.CardsFactory

class CardDeck(
    cardsFactory: CardsFactory
) {

    private val cards: MutableList<Card> = cardsFactory.generate()

    fun shuffle() {
        this.cards.shuffle()
    }

    fun pop(): Card {
        check(this.cards.isNotEmpty()) { "카드가 다 떨어졌습니다." }
        return this.cards.removeFirst()
    }
}
