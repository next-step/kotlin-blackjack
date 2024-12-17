package blackjack.view

import blackjack.domain.Player

object InputView {
    private const val SEPARATOR = ","

    fun getPlayerCommand(player: Player): PlayerCommands {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return PlayerCommands.findByCommand(readln())
    }

    fun getPlayerInfos(): List<PlayerInfo> {
        val playerNames = getPlayerNames()
        return playerNames.map { PlayerInfo(it, getBetAmountInput(it)) }
    }

    private fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(SEPARATOR)
    }

    private fun getBetAmountInput(playerName: String): Double {
        println("${playerName}의 배팅 금액은?")
        return readln().toDouble()
    }
}
