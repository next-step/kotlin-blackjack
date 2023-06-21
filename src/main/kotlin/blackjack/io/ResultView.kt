package blackjack.io

import blackjack.domain.User
import blackjack.domain.Users
import blackjack.util.PointCalculator

object ResultView {
    private const val DECK_PRINT_FORMAT = "%s카드: %s"
    private const val RESULT_PRINT_FORMAT = "%s카드: %s - 결과: %s"
    private const val GAME_OVER_MESSAGE = "게임오버"

    fun printUsersDeck(users: Users) {
        println()
        for (user in users) {
            printUserDeck(user)
        }
        println()
    }

    fun printUserDeck(user: User) {
        println(DECK_PRINT_FORMAT.format(user.name, user.deck))
    }

    fun printUsersResult(users: Users) {
        println()
        for (user in users) {
            println(
                RESULT_PRINT_FORMAT.format(
                    user.name,
                    user.deck,
                    PointCalculator.calculateUserPoint(user.deck) ?: GAME_OVER_MESSAGE,
                ),
            )
        }
    }
}
