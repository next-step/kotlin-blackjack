package blackjack.util

import blackjack.domain.user.UserDrawInterface

val TEST_USER_DRAW_INTERFACE = UserDrawInterface { true }

val TEST_USER_BET_MONEY_GETTER = { _: String -> 10000 }
