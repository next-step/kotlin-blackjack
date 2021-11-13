package blackjack.domain

class PlayerCardsHandler {
    private val cards: PlayerCards = PlayerCards()

    fun getCards(): List<Card> {
        return cards.cards.toList()
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCardsString(): String {
        return cards.toString()
    }

    fun canReceiveAdditionalCard(): Boolean {
        return cards.canReceiveAdditionalCard()
    }

    fun getCardsResultPoint(): Int {
        return cards.getResult()
    }
}
