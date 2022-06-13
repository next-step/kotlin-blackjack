package blackjack.view

import blackjack.constant.Messages
import blackjack.domain.User
import blackjack.domain.Users

/**
 * 출력을 담당하는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
object OutputView {
    fun printHandOutMessage(users: Users) {
        println()
        println(Messages.HAND_OUT_CARD.format(users.names().joinToString()))
    }

    fun printUsersCard(users: Users) {
        users.users.forEach {
            printUserCard(it)
        }
        println()
    }

    fun printUserCard(user: User) {
        println(Messages.PRINT_HAVE_CARDS.format(user.name) + user.cards.hands.joinToString())
    }

    fun printMoreCard(user: User) {
        println(Messages.WANT_MORE_CARD.format(user.name))
    }

    fun printResult(users: Users) {
        println()
        users.users.forEach {
            printCardAndScore(it)
        }
    }

    private fun printCardAndScore(user: User) {
        println(
            Messages.PRINT_CARDS_AND_SCORE.format(
                user.name,
                user.cards.hands.joinToString(),
                user.cards.getScore().value
            )
        )
    }
}
