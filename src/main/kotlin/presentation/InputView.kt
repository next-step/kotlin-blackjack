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

        fun inputAdditionalCard(player: Player): Boolean {
            println("${player.name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
            val input = readln().trim().uppercase()
            return when (input) {
                "Y" -> true
                "N" -> false
                else -> {
                    println("잘못된 입력입니다. Y 또는 N을 입력해주세요.")
                    inputAdditionalCard(player)
                }
            }
        }
    }
}
