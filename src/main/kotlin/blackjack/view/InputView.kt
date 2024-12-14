package blackjack.view

import blackjack.domain.participant.PlayerAction
import blackjack.view.dto.PlayerDto

object InputView {

    fun showAndGetPlayers(): List<PlayerDto> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
            .map { it.trim() }
            .map { name -> PlayerDto(name, showAndGetPlayersBettingAmount(name)) }
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
