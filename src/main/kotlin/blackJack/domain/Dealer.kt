package blackJack.domain

class Dealer : People("딜러") {
    private val deck = Deck()

    fun shuffleDeck() {
        deck.shuffle()
    }

    fun giveCard(people: People) {
        people.addCard(deck.getCard())
    }

    fun isOver16(): Boolean = getTotalScore() >= MINIMUM

    companion object {
        private const val MINIMUM = 17
    }
}
