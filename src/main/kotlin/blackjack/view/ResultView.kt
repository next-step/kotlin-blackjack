package blackjack.view

import blackjack.domain.BlackJackRules.INIT_CARD_DRAW_COUNT
import blackjack.domain.User

object ResultView {
    fun printInitCardReceive(users: List<User>) {
        println("${users.joinToString(", ") { it.name }}에게 ${INIT_CARD_DRAW_COUNT}장의 카드를 나누었습니다.")
    }

    fun printUserCards(
        user: User,
        printScore: Boolean,
    ) {
        val cards = user.cards.values.joinToString(", ") { "${it.rank.value}${it.suit.description}" }
        val scoreText = "- 결과: ${user.cards.calculateScore()}"
        println("${user.name}카드: $cards ${if (printScore) scoreText else ""}")
    }

    fun printAskReceiveMoreCard(user: User) {
        println("${user.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printCanNotReceivedCard() {
        println("더 이상 카드를 받을 수 없습니다")
    }

    fun printInvalidAnswer() {
        println("잘못된 입력입니다. y 또는 n 을 입력하세요")
    }
}
