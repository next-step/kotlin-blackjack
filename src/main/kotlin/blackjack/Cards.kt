package blackjack

class Cards {
    var userCards = mutableListOf(Card(), Card())
        private set

    fun addCard() {
        userCards.add(Card())
    }

    fun getCards() = userCards
    override fun toString(): String {
        return "$userCards"
    }
}
