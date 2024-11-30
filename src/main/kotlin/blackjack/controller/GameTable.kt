package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.User

private const val INIT_CARD_DRAW_COUNT = 2

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val names = (
        readlnOrNull()?.split(",")
            ?.map { it.trim() }
            ?: throw IllegalArgumentException("잘못된 입력입니다.")
    )

    val deck = Deck.create()
    val users =
        names.map { User.create(name = it) }.map { user ->
            (1..INIT_CARD_DRAW_COUNT).fold(user) { acc, _ ->
                acc.receiveCard(deck.draw())
            }
        }
    println("${users.joinToString(", ") { it.name }}에게 ${INIT_CARD_DRAW_COUNT}장의 카드를 나누었습니다.")

    users.forEach { user ->
        val cards = user.cards.values.joinToString(", ") { "${it.rank.value}${it.suit.description}" }
        println("${user.name}카드: $cards")
    }
}
