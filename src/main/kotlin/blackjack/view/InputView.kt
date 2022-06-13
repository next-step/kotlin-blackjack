package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

object InputView {
    private const val NAME_DELIMITER = ","
    private val INPUT_YES_NO_MAP = mapOf(Pair("y", true), Pair("n", false))

    fun inputPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readPlayers()
    }

    private fun readPlayers(): Players {
        val str = readLine()
        if (str.isNullOrEmpty()) {
            println("잘못된 입력입니다. 다시 입력해주세요.")
            return readPlayers()
        }

        return Players(str.split(NAME_DELIMITER).map { Player(it) })
    }

    fun inputConditionToGiveCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readYesOrNo()
    }

    private fun readYesOrNo(): Boolean {
        val str = readLine()
        if (str.isNullOrEmpty() || !INPUT_YES_NO_MAP.containsKey(str.lowercase())) {
            println("잘못된 입력입니다. 다시 입력해주세요.")
            return readYesOrNo()
        }

        return INPUT_YES_NO_MAP[str.lowercase()]!!
    }
}
