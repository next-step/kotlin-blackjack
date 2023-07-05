package blackjack.view

import blackjack.domain.Player

object InputView {
    private const val INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    fun inputPlayers(): List<Player> {
        val playerNames = inputPlayerNames()

        return playerNames.map { Player(it) }
    }

    private fun inputPlayerNames(): List<String> {
        println(INPUT_PLAYERS_NAME)
        val inputNames = InputNames(readln())

        return inputNames.parseNames()
    }
}
