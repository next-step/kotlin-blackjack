package view

import blackjack.Player

const val ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력 하세요.(쉼표를 기준으로 분리)"
const val ANSWER_DRAW = "(예는 y, 아니오는 n)"
const val ENTER_ONLY_AVAILABLE_REPLIES = "대답은 y(예), n(아니오)로만 할 수 있습니다."

object InputView {

    private fun readText(message: String): String {
        println(message)
        var text = readLine()
        while (text.isNullOrBlank()) {
            println(message)
            text = readLine()
        }
        return text
    }

    fun readPlayerNames(): String {
        return readText(message = ENTER_PLAYER_NAMES)
    }

    fun readReplyToDrawing(player: Player): String {
        var text = readText(message = "${player}는 한장의 카드를 더 받겠습니까?" + ANSWER_DRAW)
        val replies = listOf("y", "n")
        while (!replies.contains(text)) {
            text = readText(message = ENTER_ONLY_AVAILABLE_REPLIES)
        }
        return text
    }
}
