package blackjack.view

import blackjack.domain.parser.StringParser
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerAction

object InputView {

    fun showAndGetPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return StringParser.parse(readln()).map { playerName ->
            val bettingAmount = showAndGetPlayersBettingAmount(playerName)
            return@map Player(name = playerName, bettingAmount = bettingAmount)
        }
    }

    private fun showAndGetPlayersBettingAmount(playerName: String): Int {
        println("${playerName}의 배팅 금액은?")
        return readln().toInt()
    }

    fun showAndGetPlayerAction(playerName: String): PlayerAction {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return if (readln() == "y") {
            PlayerAction.HIT
        } else {
            PlayerAction.STAY
        }
    }

}
