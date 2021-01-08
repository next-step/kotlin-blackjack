package blackjack.view

import blackjack.domain.Player

const val ENTER_PLAYERS_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
const val CHOOSE_DRAW_OR_NOT = "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"

object InputView {
    private fun readText(message: String = ""): String {
        println(message)
        var text = readLine()
        while (text.isNullOrBlank()) {
            println(message)
            text = readLine()
        }
        return text
    }

    fun readSetup(): String? {
        return readText(ENTER_PLAYERS_NAMES)
    }

    fun replyDraw(player: Player): String {
        var text = readText("\n$player"+CHOOSE_DRAW_OR_NOT)
        return text
    }
}
