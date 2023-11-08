package blackjack.view

import blackjack.domain.Player

object InputView {

    fun inputPlayerName(): List<String> {
        println(INPUT_PLAYER_NAME)
        return readln().split(DEFAULT_DELIMITER).map { it.trim() }
    }

    fun inputHitOrStand(player: Player): Boolean {
        println("${player.name}$INPUT_HIT_OR_STAND")
        val value = readln().lowercase()
        runCatching { require(value == YES || value == NO) }
            .onFailure {
                println(NOT_FOUND_YES_OR_NO)
                inputHitOrStand(player)
            }

        if (yesOrNo(value) && player.burst()) {
            println("${player.name}$PLAYER_BURST")
            return false
        }
        return yesOrNo(value)
    }

    private fun yesOrNo(input: String): Boolean {
        return input == YES
    }

    private const val INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val DEFAULT_DELIMITER = ","
    private const val INPUT_HIT_OR_STAND = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val PLAYER_BURST = "는 이미 21점을 초과했습니다."
    private const val YES = "y"
    private const val NO = "n"
    private const val NOT_FOUND_YES_OR_NO = "$YES 또는 ${NO}을 입력해주세요."
}
