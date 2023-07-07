package blackjack.util

import blackjack.domain.user.UserBetMoneyGetter
import blackjack.domain.user.UserDrawChecker

val TEST_USER_DRAW_CHECKER = UserDrawChecker { true }

class FixedUserBetMoneyGetter(private val betMoney: Int = 10000) : UserBetMoneyGetter {
    override fun getBetMoney(userName: String): Int {
        return betMoney
    }
}
