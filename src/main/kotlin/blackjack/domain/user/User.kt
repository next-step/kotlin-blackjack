package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.Result

fun interface UserDrawInterface {
    fun canDraw(user: User): Boolean
}

abstract class Player(
    val name: String,
    val cards: Cards,
) {
    val finalScore: Int by lazy { cards.score() }

    fun getCardsSize() = cards.size

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun score(): Int {
        return cards.score()
    }

    fun isBust(): Boolean {
        return cards.isBust()
    }

    abstract fun canDraw(): Boolean
}

open class User(
    name: String,
    cards: Cards,
    private val userDrawInterface: UserDrawInterface,
) : Player(name, cards) {

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    fun match(dealer: Dealer): Result {
        val compareResult = when {
            dealer.isBust() -> 1
            isBust() -> -1
            else -> finalScore - dealer.finalScore
        }
        return when {
            compareResult > 0 -> Result.WIN
            compareResult < 0 -> Result.LOSE
            else -> Result.DRAW
        }
    }

    override fun canDraw(): Boolean {
        return !isBust() && userDrawInterface.canDraw(this)
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}

class Dealer(cards: Cards) : Player(DEALER_NAME, cards) {

    override fun canDraw(): Boolean {
        return score() <= DEALER_HIT_THRESHOLD
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
