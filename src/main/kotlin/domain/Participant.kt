package domain

interface Participant {
    val name: String
    val cards: Cards

    fun getPublicCardsOnFirstRound(): Cards

    fun drawCardFromDeck(deck: CardDeck) {
        cards.addCard(deck.drawCard())
    }

    fun isBust(): Boolean {
        return cards.isBust()
    }

    fun calculateScore(): Int {
        return cards.calculateScore()
    }

    fun calculateScoreTreatAceAsOne(): Int {
        return cards.calculateScoreTreatAceAsOne()
    }
}
