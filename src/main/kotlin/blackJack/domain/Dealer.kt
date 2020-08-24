package blackJack.domain

class Dealer : Person("딜러") {
    val deck = Deck()

    fun shuffleDeck(cards: List<Card> = Deck.DEFAULT_DECK) {
        deck.shuffle(cards)
    }

    fun giveCard(person: Person) {
        person.addCard(deck.getFirstCard())
    }

    override fun addCard(card: Card) {
        if (canAddCard()) {
            super.addCard(card)
        } else {
            throw IllegalArgumentException("딜러의 카드 총합이 17을 넘겼기 때문에 카드를 더 받을수 없습니다.")
        }
    }

    fun canAddCard(): Boolean = getTotalScore() < MINIMUM

    companion object {
        private const val MINIMUM = 17
    }
}
