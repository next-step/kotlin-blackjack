package model

class Cards {
    private val values = mutableListOf<Card>()

    fun add(card: Card) {
        values.add(card)
    }

    fun get(): List<Card> {
        return values
    }

    fun isDuplicate(cardNumber: CardNumber, cardShape: CardShape): Boolean {
        val duplicatedCards = values.count { it.cardNumber == cardNumber && it.cardShape == cardShape }
        return duplicatedCards != 0
    }
}
