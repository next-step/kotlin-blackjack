package blackJack.domain

class Dealer : Person("딜러") {
    private val deck = Deck()

    fun shuffleDeck(cards: List<Card>) {
        deck.shuffle { cards.shuffled() }
    }

    fun giveCard(person: Person) {
        person.addCard(deck.getCard())
    }

    fun takeCard() {
        while (!isBust() && canAddCard(getTotalScore())) {
            giveCard(this)
        }
    }

    private fun canAddCard(totalScore: Int): Boolean = totalScore < MINIMUM

    fun getDeckSize(): Int = deck.cards.size

    companion object {
        private const val MINIMUM = 17
    }
}
