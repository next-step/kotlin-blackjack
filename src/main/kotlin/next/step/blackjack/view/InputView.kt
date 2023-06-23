package next.step.racing.view

import next.step.blackjack.domain.Player
import next.step.blackjack.view.PlayerParser


object InputView {

    private const val ENTER_PLAYER = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ENTER_TURN = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val YES = "y"
    private const val NO = "n"

    fun readPlayers(): Set<Player> = read(ENTER_PLAYER) { PlayerParser.parse(it) }

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

    fun readTurn(player: Player): Boolean = read("${player.name}${ENTER_TURN}") {
        when {
            it.trim() == YES -> true
            it.trim() == NO -> false
            else -> throw IllegalArgumentException("y나 n 중에 입력하세요.")
        }
    }


}
