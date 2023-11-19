package blackjack.view

import blackjack.model.state.PlayerState

object InputView {
    private const val DELIMITER = ","
    private const val MINIMUM_PLAYER = 1
    private const val MAXIMUM_PLAYER = 8
    private const val INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INVALID_PLAYER_NAME_EXCEPTION = "플레이어 수는 구분자 기준 ${MINIMUM_PLAYER}명에서 ${MAXIMUM_PLAYER}명까지 가능합니다"
    private const val INPUT_PLAYER_CHOICE_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n) "
    private const val INVALID_CHOICE_INPUT_EXCEPTION = "y 또는 n 으로 입력하세요."

    fun inputPlayers(): List<String> {
        println(INPUT_PLAYER_NAME_MESSAGE)
        return validateInputPlayer(readln())
    }

    fun inputPlayerChoice(name: String): PlayerState {
        print("\n$name" + INPUT_PLAYER_CHOICE_MESSAGE)
        return validateChoice(readln())
    }

    private fun validateInputPlayer(players: String): List<String> {
        val result = players.split(DELIMITER).map { it.trim() }
        require(result.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) { INVALID_PLAYER_NAME_EXCEPTION }
        return result
    }

    private fun validateChoice(choice: String): PlayerState {
        require(choice == "y" || choice == "n") { INVALID_CHOICE_INPUT_EXCEPTION }
        return if (choice == "y") PlayerState.HIT else PlayerState.STAY
    }
}
