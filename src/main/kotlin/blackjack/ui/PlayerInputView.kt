package blackjack.ui

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.state.notstarted.NotStarted

object PlayerInputView {
    fun askPlayerNames(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readLine() ?: throw IllegalArgumentException("입력된 이름이 없습니다.")
        println()

        val playerList = names.split(",").map {
            val price = askBetAmount(it)
            Player(it, NotStarted(), price)
        }
        return Players(playerList)
    }

    private fun askBetAmount(name: String): Int {
        println("${name}의 배팅 금액은?")
        val price = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("잘못된 금액이 들어왔습니다.")
        println()
        return price
    }

    fun askMoreCard(name: String): Boolean {
        println("${name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val agreeYn = readLine() ?: throw IllegalArgumentException("입력된 응답이 없습니다.")
        return when (agreeYn) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주십시오.")
        }
    }
}
