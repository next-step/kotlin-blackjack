package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object InputView {
    private const val CONTINUE_OR_STOP_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun enterParticipatingPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = readln()
        return Players.create(playerNames)
    }

    fun enterIsContinueDrawCard(player: Player) {
        val userInput = getIsContinueDraw(player)
        when (userInput.lowercase()) {
            "n" -> player.stopCardDraw()
            "y" -> player.continueCardDraw()
            else -> println("잘못된 입력입니다. 다시 입력해주세요.")
        }
    }

    fun getIsContinueDraw(player: Player): String {
        println()
        println(player.name + CONTINUE_OR_STOP_MESSAGE)
        return readln()
    }
}
