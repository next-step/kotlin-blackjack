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
    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

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

    fun match(player: Player): Result {
        if (isBust()) {
            return Result.LOSE
        }
        if (player.isBust()) {
            return Result.WIN
        }

        return when {
            finalScore > player.finalScore -> Result.WIN
            finalScore < player.finalScore -> Result.LOSE
            else -> Result.DRAW
        }
    }

    abstract fun canDraw(): Boolean

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}

class User(
    name: String,
    cards: Cards,
    private val userDrawInterface: UserDrawInterface,
) : Player(name, cards) {

    override fun canDraw(): Boolean {
        return !isBust() && userDrawInterface.canDraw(this)
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