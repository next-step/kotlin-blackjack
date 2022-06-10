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
        println(Messages.HAND_OUT_CARD.format(users.users.joinToString(", ") { it.name }))
    }

    fun printUsersCard(users: Users) {
        users.users.forEach {
            printUserCard(it)
        }
    }

    fun printUserCard(user: User) {
        println(Messages.PRINT_HAVE_CARDS.format(user.name) + user.cards)
    }
}
