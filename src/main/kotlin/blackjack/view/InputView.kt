package blackjack.view

import blackjack.Player

class InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.")
        return readln().split(",").onEach { it.trim() }
    }

    fun inputHitDecision(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겟습니까?(예는 y, 아니오는 n)")
        return readln().trim() == "y"
    }
}
