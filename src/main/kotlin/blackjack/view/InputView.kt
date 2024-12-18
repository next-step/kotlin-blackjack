package blackjack.view

import blackjack.domain.player.Player

class InputView {
    fun inputUsers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln()
    }

    fun startGame(
        dealer: Player,
        users: List<Player>,
        count: Int,
    ) {
        println("${dealer.name}와 ${users.joinToString { it.name }}에게 ${count}장을 나누었습니다.")
        println()
    }

    fun startUser() {
        println()
    }

    fun inputNextDecision(name: String): String {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln()
    }

    fun printAddCardDealer(points: Int) {
        println("딜러는 ${points}이하라 한장의 카드를 더 받았습니다.")
    }
}
