package blackjack.business

class Player(val name: String, val cards: PlayerCards = PlayerCards()) {

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun canDrawCard(): Boolean {
        return cards.canDrawCard()
    }
}
