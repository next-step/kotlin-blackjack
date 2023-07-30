package blackjack.view

import blackjack.domain.Player

class InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.")
        return readln().split(",").onEach { it.trim() }
    }

    fun inputBettingAmount(playerName: String): Int {
        println("${playerName}의 배팅 금액은?")
        return readln().toInt()
    }

    fun inputHitDecision(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겟습니까?(예는 y, 아니오는 n)")
        return readln().trim() == "y"
    }
}
