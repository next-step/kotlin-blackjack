package next.step.racing.view

import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerNames
import next.step.blackjack.view.PlayerNameParser

object InputView {

    private const val ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ENTER_TURN = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES = "y"
    private const val NO = "n"

    fun readPlayerNames(): PlayerNames = read(ENTER_PLAYER_NAMES) { PlayerNameParser.parse(it) }

    private fun <T> read(enterMsg: String, constructor: (String) -> T): T {
        return runCatching {
            println(enterMsg)
            constructor(readln())
        }.onSuccess {
            return it
        }.onFailure { e ->
            OutputView.showError(e.message)
            return read(enterMsg, constructor)
        }.getOrThrow()
    }

    fun readTurn(player: Player): Boolean = read("${player.name()}$ENTER_TURN") {
        when {
            it.trim() == YES -> true
            it.trim() == NO -> false
            else -> throw IllegalArgumentException("y나 n 중에 입력하세요.")
        }
    }
}
