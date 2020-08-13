package blackJack.domain

class Dealer : People("딜러") {
    private val deck = Deck()

    fun shuffleDeck() {
        deck.shuffle()
    }

    fun giveCard(people: People) {
        people.addCard(deck.getCard())
    }

    fun isOver16(): Boolean = getTotalScore() > 16
}
