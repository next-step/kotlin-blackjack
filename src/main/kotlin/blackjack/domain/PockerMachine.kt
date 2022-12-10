package blackjack.domain

import blackjack.domain.strategy.SequentialCardPickStrategy

class PockerMachine(
    private val cardDeck: CardDeck = CardDeck(),
    private val dealer: Dealer = Dealer(SequentialCardPickStrategy())
) {
    fun initialize(person: Person) {
        List(BASIC_CARD_COUNT) {
            person.apply { addCard(dealer.pickCard(cardDeck)) }
        }
    }

    fun addCard(func: (person: Person) -> Boolean, person: Person) {
        while (func(person)) {
            val card = dealer.pickCard(cardDeck)
            person.addCard(card)
        }
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
    }
}
