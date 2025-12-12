package presentation

import domain.Player

class InputView {
    companion object {
        fun inputPlayers(): Set<Player> {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            return readln()
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { Player(it) }
                .toSet()
        }
    }
}
