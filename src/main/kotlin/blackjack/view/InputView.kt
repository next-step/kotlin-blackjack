package blackjack.view

import blackjack.domain.Player

class InputView {
    fun inputPlayers(): List<String> {
        println(PLAYER_NAMES_INPUT_GUIDE)
        val names = readln()
        return names.split(PLAYER_NAMES_DELIMITER)
    }

    fun printSetUp(players: List<Player>) {
        TODO()
    }

    companion object {
        private const val PLAYER_NAMES_INPUT_GUIDE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val PLAYER_NAMES_DELIMITER = ","
    }
}
