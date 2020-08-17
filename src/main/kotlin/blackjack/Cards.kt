package blackjack

data class Cards(private var userCards: MutableList<Card>) {
    fun getCards() = userCards

    fun addCard(card: Card) {
        userCards.add(card)
    }

    fun isGraterThanWinScore(card: Card) = sumCardNumbers() + card.getCardScore() > WIN_SCORE

    fun sumCardNumbers(): Int {
        return userCards.map { it.getCardScore() }.sum()
    }

    override fun toString(): String {
        return "$userCards"
    }

    companion object {
        const val WIN_SCORE = 21
    }
}
