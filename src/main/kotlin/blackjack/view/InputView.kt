package blackjack.view

import blackjack.model.Gamer

object InputView {

    fun requestPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()?.split(",") ?: emptyList()
    }

    fun requestGambleMoney(playerNames: List<String>): List<Int> {
        val gambleMoneyPerPlayer = mutableListOf<Int>()
        playerNames.map {
            println("${it}의 배팅 금액은?")
            gambleMoneyPerPlayer.add(readLine()?.toInt() ?: 0)
        }
        return gambleMoneyPerPlayer
    }

    fun requestOneOfCard(player: Gamer): String {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readLine() ?: "n"
    }
}
