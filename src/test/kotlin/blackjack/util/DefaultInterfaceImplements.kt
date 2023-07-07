package blackjack.util

import blackjack.domain.user.UserDrawChecker

val TEST_USER_DRAW_CHECKER = UserDrawChecker { true }

val TEST_USER_BET_MONEY_GETTER = { _: String -> 10000 }
