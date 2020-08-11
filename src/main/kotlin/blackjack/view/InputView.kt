package blackjack.view

import blackjack.domain.Player

const val ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

object InputView {

    fun readPlayerNames(): String {
        return readText(message = ENTER_PLAYER_NAMES)
    }

    fun readReplyToQuestionOfDrawingOrNot(player: Player): String {
        return readText(message = "${player}는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
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
