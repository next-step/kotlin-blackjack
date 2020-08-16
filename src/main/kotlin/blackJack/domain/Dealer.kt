package blackJack.domain

class Dealer : Person("딜러") {
    private val deck = Deck()

    fun shuffleDeck() {
        deck.shuffle { it.shuffled() }
    }

    fun giveCard(person: Person) {
        person.addCard(deck.getCard())
    }

    fun overTotalScore16(totalScore: Int = getTotalScore()): Boolean = totalScore >= MINIMUM

    companion object {
        private const val MINIMUM = 17
    }
}
