package blackjack.io

import blackjack.domain.User
import blackjack.domain.Users

object ResultView {
    private const val DECK_PRINT_FORMAT = "%s카드: %s"

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
}
