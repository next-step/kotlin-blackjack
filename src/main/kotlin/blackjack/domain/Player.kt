package blackjack.domain

open class Player(val name: String, protected val hand: Hand = Hand()) {
    open fun canDraw(): Boolean {
        return !score().isBurst()
    }

    open fun open(): List<Card> {
        return hand.cards()
    }

    fun draw(card: Card) {
        hand.addCard(card)
    }

    fun score(): Score {
        return hand.score()
    }

    fun result(dealerScore: Score): Result {
        return when {
            dealerScore.isBurst() -> Result.WIN
            score().isBurst() -> Result.LOSE
            score().value > dealerScore.value -> Result.WIN
            else -> Result.LOSE
        }
    }
}
