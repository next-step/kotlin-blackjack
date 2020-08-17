package blackjack

data class Cards(private var userCards: MutableList<Card>) {
    private var score: Int = sumCardNumbers()

    fun getCards() = userCards
    fun getScore() = score

    fun addCard(card: Card) {
        userCards.add(card)
        score = sumCardNumbers()
    }

    fun isGraterThanWinScore(card: Card) = score + card.getCardScore() > WIN_SCORE

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
