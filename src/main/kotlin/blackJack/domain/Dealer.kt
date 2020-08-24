package blackJack.domain

class Dealer : Person("딜러") {
    val deck = Deck()

    fun shuffleDeck(cards: List<Card> = Deck.DEFAULT_DECK) {
        deck.shuffle(cards)
    }

    fun giveCard(person: Person) {
        person.addCard(deck.getFirstCard())
    }
}
