package blackjack.view

import blackjack.domain.player.PlayerName

object ConsoleInputView {

    private const val NAME_DELIMITER = ","

    fun getNames(): List<String> {
        val input = getInput("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return input.split(NAME_DELIMITER).map { it.trim() }
    }

    fun getBetMoney(name: PlayerName): Int {
        val input = getInput("${name.value}의 배팅 금액은?")
        return input.toInt()
    }

    fun getAnswer(name: String): String {
        println()
        return getInput("${name}은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }


    private fun getInput(message: String): String {
        println(message)
        return readLine()!!
    }
}
