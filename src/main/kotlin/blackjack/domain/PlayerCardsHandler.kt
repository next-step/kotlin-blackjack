package blackjack.domain

class PlayerCardsHandler {
    private val cards: PlayerCards = PlayerCards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun canReceiveAdditionalCard(): Boolean {
        return cards.canReceiveAdditionalCard()
    }

    fun getCards(): List<Card> {
        return cards.cards.toList()
    }

    fun getCardsString(): String {
        return cards.toString()
    }

    fun getCardsResultPoint(): Int {
        return cards.getCardsResultPoint()
    }
}
