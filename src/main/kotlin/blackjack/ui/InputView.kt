package blackjack.ui

import blackjack.domain.BettingMoney
import blackjack.domain.Player

object InputView {

    private const val INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PLAYER_NAME_DELIMITER = ","
    private const val HIT_ANSWER = "y"
    private const val STAY_ANSWER = "n"

    fun inputNames(): List<String> {
        println(INPUT_PLAYER_NAMES_MESSAGE)
        return readln().split(PLAYER_NAME_DELIMITER)
    }

    fun inputHitOrStay(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val inputHitOrStop = readln()

        try {
            checkValidInputHitOrStay(inputHitOrStop)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            return inputHitOrStay(player)
        }
        return HIT_ANSWER == inputHitOrStop
    }

    private fun checkValidInputHitOrStay(inputHitOrStop: String) {
        require(inputHitOrStop == HIT_ANSWER || inputHitOrStop == STAY_ANSWER) {
            "$HIT_ANSWER 또는 $STAY_ANSWER 만 입력가능합니다. 다시 입력해주세요."
        }
    }

    fun inputBets(inputNames: List<String>): Map<String, BettingMoney> {
        val inputNameAndBetMap = mutableMapOf<String, BettingMoney>()
        for (inputName in inputNames) {
            println()
            println("${inputName}의 배팅 금액은?")
            val bettingMoney = BettingMoney(readln().toInt())
            inputNameAndBetMap.put(inputName, bettingMoney)
        }
        return inputNameAndBetMap
    }
}
