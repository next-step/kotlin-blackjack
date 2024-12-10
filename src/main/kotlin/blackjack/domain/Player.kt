package blackjack.domain

open class Player(val name: String) {
    private var hand: Hand = Hand()

    fun addCards(newCards: List<Card>) {
        hand = hand.addCards(newCards)
    }

    val cards: List<Card> get() = hand.getCards()
    val score: Int get() = hand.calculateScore()

    open fun canContinue(): Boolean {
        return score <= 21
    }

    fun compareWithDealer(dealer: Dealer): String {
        return when {
            score > 21 || dealer.score > score -> "패"
            score == dealer.score -> "무승부"
            else -> "승"
        }
    }
}