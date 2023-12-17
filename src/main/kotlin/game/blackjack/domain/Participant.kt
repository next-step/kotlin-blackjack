package game.blackjack.domain

class Participant(val name: String) {
    private val handCards: HandCards = HandCards()

    fun drawCard(card: Card) = handCards.add(card)

    fun drawCards(cards: List<Card>) = handCards.addAll(cards)

    fun isBust() = handCards.getCurrentScore() > Blackjack.WINNING_SCORE

    fun isNotBust() = !isBust()

    fun getScore() = handCards.getCurrentScore()

    fun getHandSize() = handCards.size

    override fun toString() = "${name}카드: $handCards"
}
