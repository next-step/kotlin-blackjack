package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName

object InputView {

    private const val SEPERATOR = ","

    fun getInputPlayers(): MutableList<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(SEPERATOR)
        return names.map { Player(PlayerName(it)) }.toMutableList()
    }

    fun isMoreCard(player: Player): Boolean {
        println("${player.name.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().trim() == "y"
    }
}
