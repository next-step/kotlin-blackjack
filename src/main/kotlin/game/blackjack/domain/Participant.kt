package game.blackjack.domain

class Participant(val name: String) {
    val handCards: HandCards = HandCards()

    fun drawCard(card: Card) {
        handCards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        handCards.add(cards)
    }

    fun isBust(): Boolean {
        return handCards.getCurrentScore() > Blackjack.WINNING_SCORE
    }

    fun isNotBust(): Boolean {
        return !isBust()
    }

    fun getScore(): Int {
        return handCards.getCurrentScore()
    }

    override fun toString(): String {
        return "${name}카드: $handCards"
    }
}
