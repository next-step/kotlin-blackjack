package blackjack.view

import blackjack.domain.GameTable.Companion.INIT_CARD_DRAW_COUNT
import blackjack.domain.User

object ResultView {
    fun printInitCardReceive(users: List<User>) {
        println("${users.joinToString(", ") { it.name }}에게 ${INIT_CARD_DRAW_COUNT}장의 카드를 나누었습니다.")
    }

    fun printUsersCard(
        users: List<User>,
        printScore: Boolean,
    ) {
        users.forEach { printUserCard(user = it, printScore = printScore) }
    }

    fun printUserCard(
        user: User,
        printScore: Boolean,
    ) {
        val cards = user.cards.values.joinToString(", ") { "${it.rank.value}${it.suit.description}" }
        val scoreText = "- 결과: ${user.cards.score}"
        println("${user.name}카드: $cards ${if (printScore) scoreText else ""}")
    }

    fun printCanNotReceivedCard() {
        println("더 이상 카드를 받을 수 없습니다")
    }

    fun linebreak() {
        println()
    }
}
