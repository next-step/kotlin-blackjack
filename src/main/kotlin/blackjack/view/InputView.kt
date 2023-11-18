package blackjack.view

object InputView {
    private const val INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DELIMITER = ","
    private const val INVALID_PLAYER_NAME_EXCEPTION = "플레이어 이름을 구분자 기준으로 두 명 입력하세요."

    fun inputPlayers(): List<String> {
        println(INPUT_PLAYER_NAME_MESSAGE)
        return validateInputPlayer(readln())
    }

    private fun validateInputPlayer(players: String): List<String> {
        val result = players.split(DELIMITER).map { it.trim() }
        require(result.size == 2) { INVALID_PLAYER_NAME_EXCEPTION }
        return result
    }
}
