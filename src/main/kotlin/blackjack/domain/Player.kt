package blackjack.domain

class Player(val name: String, val cards: MutableList<String> = mutableListOf()) {
    fun addCards(newCards: List<Pair<CardNumber, CardShape>>) {
        cards.addAll(newCards.map { "${it.first} of ${it.second}" })
    }

    fun getCardList(): List<String> = cards
}
