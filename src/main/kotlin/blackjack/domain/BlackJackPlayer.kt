package blackjack.domain

interface BlackJackPlayer {
    val name: String
    val cards: Cards
    val cardSet: Set<Card>
    fun drawBy(trumpCard: TrumpCard) {
        cards.add(trumpCard.draw())
    }
    fun isBurst(): Boolean {
        return cards.score().burst()
    }
    fun isHit(): Boolean
}
