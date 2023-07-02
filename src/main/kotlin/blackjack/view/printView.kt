package blackjack.view

import blackjack.domain.BlackjackGame.Companion.GAME_START_CARD_COUNT
import blackjack.domain.User

fun printLine() {
    println()
}

fun printUserNames(names: String) {
    println("${names}에게 ${GAME_START_CARD_COUNT}장의 카드를 나누었습니다.")
}

fun printUserCards(userName: String, cards: String) {
    println("${userName}카드: $cards")
}

fun printResults(user: User) {
    println("${user.name}카드: ${user.cardNames()} - 결과: ${user.cardValues()}")
}
