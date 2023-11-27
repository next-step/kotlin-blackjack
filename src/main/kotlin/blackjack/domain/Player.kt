package blackjack.domain

class Player(
    val name: String,
    private val hand: Hand = Hand(),
    private var isEnded: Boolean = false
) {
    fun getCardList(): List<Card> {
        return hand.getCardList()
    }

    fun canDraw(): Boolean =
        getScore() <= BlackjackRule.targetScore && !isEnded

    fun draw(deck: Deck) {
        hand.add(card = deck.pop())
    }

    fun endTurn() {
        isEnded = true
    }

    fun getScore(): Int {
        val possibleScore = hand.getPossibleScore()

        if (possibleScore.count() == 1) {
            return possibleScore.first()
        }

        return hand.getPossibleScore().filter {
            it <= BlackjackRule.targetScore
        }.let {
            if (it.isNotEmpty()) {
                return it.max()
            }

            hand.getPossibleScore().min()
        }
    }
}
