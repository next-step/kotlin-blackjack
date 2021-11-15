package blackjack.view

import blackjack.model.Names
import blackjack.model.Player

class InputView {

    fun getNames(): Names? {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()?.let { Names.parse(it) }
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
    }
}
