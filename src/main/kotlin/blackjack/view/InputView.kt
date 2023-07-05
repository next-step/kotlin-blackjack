package blackjack.view

import blackjack.domain.GameMoney
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName

object InputView {

    private const val SEPERATOR = ","

    fun getInputPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(SEPERATOR)
        println()
        return names.map {
            val name = PlayerName(it)
            val money = getBettingMoney(it)
            Player(name, money)
        }.toList()
    }

    private fun getBettingMoney(name: String): GameMoney {
        println("${name}의 배팅 금액은?")
        return GameMoney.of(readln())
    }

    fun inputIsMoreCard(player: Player): Boolean {
        println("${player.name.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().trim() == "y"
    }
}
