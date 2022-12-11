package model

private const val BASE_SCORE_DEALER_MORE_HIT = 17

class Dealer : Player(name = "딜러") {

    private val deck: Deck = Deck()
    fun pick(): Card = deck.pick()
    fun cardCount(): Int = deck.count()

    fun hitIfRequired() {
        val pokerScore = PokerScore(cards)
        if (pokerScore.score < BASE_SCORE_DEALER_MORE_HIT) {
            hit(pick())
        }
    }
}
