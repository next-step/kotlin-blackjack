package study.blackjack.view

import study.blackjack.model.BlackjackUser
import study.blackjack.model.Money

/**
 * @author 이상준
 */
class InputView {
    fun inputPlayerNames(): List<BlackjackUser> {
        println(INPUT_PLAYER_NAME_MESSAGE)
        val names = readlnOrNull() ?: throw IllegalArgumentException()
        return names.split(",").map { BlackjackUser(name = it) }
    }

    fun inputPlayerMoney(player: BlackjackUser) {
        println("${player.name}의 배팅 금액은?")
        val amount = readlnOrNull()?.toInt() ?: throw IllegalArgumentException()
        player.money(amount)
        println()
    }

    fun inputGiveCardMessage(player: BlackjackUser): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val use = readlnOrNull() ?: throw IllegalArgumentException()
        return use == "y"
    }

    companion object {
        private const val INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
