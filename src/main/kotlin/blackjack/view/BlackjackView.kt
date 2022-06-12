package blackjack.view

import blackjack.domain.Player

object BlackjackView {

    fun printPlayerInput() {
        println(PLAYER_INPUT_MESSAGE)
    }

    fun printInitialize(players: String) {
        println("$players$BLACKJACK_INIT_MESSAGE")
    }

    fun printPlayerCard(name: String, cards: String) {
        println("${name}카드: $cards")
    }

    fun printMoreCard(name: String) {
        println("${name}$MORE_CARD_MESSAGE")
    }

    fun printDrawingFailed(message: String) {
        println(message)
    }

    fun printResult(name: String, cards: String, points: Int) {
        println("${name}카드: $cards - 결과: $points")
    }

    private const val PLAYER_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BLACKJACK_INIT_MESSAGE = "에게 2장의 나누었습니다."
    private const val MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}