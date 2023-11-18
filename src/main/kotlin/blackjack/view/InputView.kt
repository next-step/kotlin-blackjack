package blackjack.view

object InputView {
    private const val DELIMITER = ","
    private const val MINIMUM_PLAYER = 1
    private const val MAXIMUM_PLAYER = 8
    private const val INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INVALID_PLAYER_NAME_EXCEPTION = "플레이어 수는 구분자 기준 ${MINIMUM_PLAYER}명에서 ${MAXIMUM_PLAYER}명까지 가능합니다"

    fun inputPlayers(): List<String> {
        println(INPUT_PLAYER_NAME_MESSAGE)
        return validateInputPlayer(readln())
    }

    private fun validateInputPlayer(players: String): List<String> {
        val result = players.split(DELIMITER).map { it.trim() }
        require(result.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) { INVALID_PLAYER_NAME_EXCEPTION }
        return result
    }
}
