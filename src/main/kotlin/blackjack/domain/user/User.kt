package blackjack.domain.user

import blackjack.domain.card.Cards
import blackjack.domain.result.MatchResult

fun interface UserDrawChecker {
    fun checkDraw(user: User): Boolean
}

fun interface UserBetMoneyGetter {
    fun getBetMoney(userName: String): Int
}

class User(
    name: String,
    cards: Cards,
    private val userDrawChecker: UserDrawChecker,
    val betMoney: Int,
) : Player(name, cards) {

    init {
        require(betMoney > 0) { INVALID_BET_MONEY_ERROR_MESSAGE }
    }

    override fun checkHit(): Boolean {
        return !isBust() && userDrawChecker.checkDraw(this)
    }

    fun getUserProfit(otherPlayer: Player): Int {
        return when (match(otherPlayer)) {
            MatchResult.BLACKJACK_WIN -> (betMoney * 1.5).toInt()
            MatchResult.WIN -> betMoney
            MatchResult.DRAW -> 0
            MatchResult.LOSE -> -betMoney
        }
    }

    companion object {
        private const val INVALID_BET_MONEY_ERROR_MESSAGE = "배팅 금액은 0보다 커야합니다"
    }
}
