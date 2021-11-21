package blackjack.view

import blackjack.model.Bet
import blackjack.model.Name
import blackjack.model.Names
import blackjack.model.Player
import blackjack.util.readInt

class InputView {

    fun getPlayers(): List<Player> = getNames()
        ?.toList()
        ?.map { name ->
            println("${name}의 배팅 금액은? ")
            Player.ready(name, Bet(readInt()))
        }
        ?: emptyList()

    private fun getNames(): Names? {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val input = readLine() ?: return null
        return input.split(NAME_DELIMITER)
            .map { Name.valueOf(it.trim()) }
            .let(Names::from)
    }

    fun askDraw(player: Player): DrawAction {
        println("${player.name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readLine()
            ?.let { symbol ->
                when (symbol.uppercase()) {
                    ASK_DRAW_YES -> DrawAction.YES
                    ASK_DRAW_NO -> DrawAction.NO
                    else -> DrawAction.NO
                }
            }
            ?: DrawAction.NO
    }

    companion object {
        private const val ASK_DRAW_YES = "Y"
        private const val ASK_DRAW_NO = "N"

        private const val NAME_DELIMITER = ","
    }
}
