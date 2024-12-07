package blackjack.domain

data class Player(val playerName: PlayerName, val mutableCards: MutableCards) {
    fun addCard(card: Card) {
        mutableCards.add(card)
        require(sumCardValues() <= MutableCards.MAX_SUM_CARD_VALUES) {
            playerName.name + " Bust! / " + mutableCards.cardsToString() }
    }

    fun cardsToString(): String {
        return mutableCards.cardsToString()
    }

    fun sumCardValues(): Int {
        return mutableCards.sumValues()
    }
}
