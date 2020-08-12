package blackjack.view

import blackjack.domain.Player

const val ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
const val REPLY_YES = "y"
const val REPLY_NO = "n"

object InputView {

    fun readPlayerNames(): String {
        return readText(message = ENTER_PLAYER_NAMES)
    }

    fun readReplyToDrawing(player: Player): String {
        var text = readText(message = "${player}는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val availableReply = listOf(REPLY_YES, REPLY_NO)
        while (!availableReply.contains(text)) {
            text = readText(message = "대답은 y(예), n(아니오)로만 할 수 있습니다.")
        }
        return text
    }

    private fun readText(message: String = ""): String {
        println(message)
        var text = readLine()
        while (text.isNullOrBlank()) {
            println(message)
            text = readLine()
        }
        return text
    }
}
