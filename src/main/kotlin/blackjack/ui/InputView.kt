package blackjack.ui

import blackjack.domain.Players

object InputView {

    private const val INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PLAYER_NAME_DELIMITER = ","

    fun inputNames(): Players {
        println(INPUT_PLAYER_NAMES_MESSAGE)
        val inputNames = readln().split(PLAYER_NAME_DELIMITER)
        return Players.init(inputNames)
    }
}
