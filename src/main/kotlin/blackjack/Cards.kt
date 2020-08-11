package blackjack

class Cards {
    var userCards = mutableListOf(Card(), Card())
        private set

    fun getCards() = userCards
}
