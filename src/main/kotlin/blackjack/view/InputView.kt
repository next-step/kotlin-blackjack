package blackjack.view

import blackjack.domain.Player

object InputView {
    private const val INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private val INPUT_DRAW_RESPONSE =
        { playerName: String -> "${playerName}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)" }

    fun inputPlayers(): List<Player> {
        val playerNames = inputPlayerNames()

        return playerNames.map { Player(it) }
    }

    private fun inputPlayerNames(): List<String> {
        println(INPUT_PLAYERS_NAME)
        val inputNames = InputNames(readln())

        return inputNames.parseNames()
    }

    fun inputDrawResponse(player: Player): Boolean {
        println(INPUT_DRAW_RESPONSE(player.name))
        val response = readln()
        require(response == "y" || response == "n") { "응답은 y, n만 가능합니다" }

        return response == "y"
    }
}
