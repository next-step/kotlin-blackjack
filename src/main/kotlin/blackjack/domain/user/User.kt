package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.Result

fun interface UserDrawInterface {
    fun canDraw(user: User): Boolean
}

fun interface GetUserBetMoneyInterface {
    fun getBetMoney(userName: String): Int
}

abstract class Player(
    val name: String,
    val cards: Cards,
) {
    var score: Int
        private set

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
        score = cards.score()
    }

    fun getCardsSize() = cards.size

    fun addCard(card: Card) {
        cards.addCard(card)
        score = cards.score()
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
            score > player.score -> Result.WIN
            score < player.score -> Result.LOSE
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
    private val betMoney: Int,
) : Player(name, cards) {

    init {
        require(betMoney > 0) { INVALID_BET_MONEY_ERROR_MESSAGE }
    }

    override fun canDraw(): Boolean {
        return !isBust() && userDrawInterface.canDraw(this)
    }

    companion object {
        private const val INVALID_BET_MONEY_ERROR_MESSAGE = "배팅 금액은 0보다 커야합니다"
    }
}

class Dealer(cards: Cards) : Player(DEALER_NAME, cards) {

    override fun canDraw(): Boolean {
        return score <= DEALER_HIT_THRESHOLD
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
