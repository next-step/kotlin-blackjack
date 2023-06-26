package blackjack.domain

open class User(val name: String, val deck: Deck) : Comparable<User> {

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    fun getDeckSize() = deck.size

    fun addCard(card: Card) {
        deck.addCard(card)
    }

    fun calculatePoint(): Int {
        return deck.sum()
    }

    fun isBust(): Boolean {
        return deck.sum() > PointCalculator.BLACKJACK_LIMIT
    }

    override fun compareTo(other: User): Int {
        return deck.sum().compareTo(other.deck.sum())
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}

class Dealer(deck: Deck) : User(DEALER_NAME, deck) {
    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
