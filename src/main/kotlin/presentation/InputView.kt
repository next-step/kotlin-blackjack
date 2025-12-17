package presentation

import domain.Player

class InputView {
    companion object {
        /**
         * 플레이어를 입력받으며, 정확하게 입력되지 않는 경우 반복됩니다.
         */
        fun readPlayers(): Set<Player> {
            do {
                println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
                val players = readln()
                    .split(",")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .map { Player(it) }
                    .toSet()

                if (players.isNotEmpty()) {
                    return players
                }

                println("오류: 한 명 이상의 유효한 플레이어 이름을 쉼표로 구분하여 다시 입력해 주세요.\n")

            } while(true)
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
