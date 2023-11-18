package blackjack.ui

import blackjack.domain.Player

object InputView {

    private const val INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PLAYER_NAME_DELIMITER = ","
    private const val HIT_ANSWER = "y"
    private const val STAND_ANSWER = "n"

    fun inputNames(): List<String> {
        println(INPUT_PLAYER_NAMES_MESSAGE)
        return readln().split(PLAYER_NAME_DELIMITER)
    }

    fun inputHitOrStand(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val inputHitOrStop = readln()

        try {
            checkValidInputHitOrStand(inputHitOrStop)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            return inputHitOrStand(player)
        }
        return HIT_ANSWER == inputHitOrStop
    }

    private fun checkValidInputHitOrStand(inputHitOrStop: String) {
        require(inputHitOrStop == HIT_ANSWER || inputHitOrStop == STAND_ANSWER) {
            "$HIT_ANSWER 또는 $STAND_ANSWER 만 입력가능합니다. 다시 입력해주세요."
        }
    }
}
