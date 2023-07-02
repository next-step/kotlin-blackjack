package blackjack.view

import blackjack.domain.BlackjackGame.Companion.GAME_START_CARD_COUNT

fun printUserNames(names: String) {
    println("${names}에게 ${GAME_START_CARD_COUNT}장의 카드를 나누었습니다.")
}
