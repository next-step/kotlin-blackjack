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

    fun addCard(
        retryFunc: (person: Person) -> Boolean,
        person: Person,
        printFunc: (person: Person) -> Unit
    ) {
        while (retryFunc(person)) {
            val card = dealer.pickCard(cardDeck)
            person.addCard(card)
            printFunc(person)
        }
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
    }
}
