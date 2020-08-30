package blackjack.view

import blackjack.model.participant.Player

private const val ENTER_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
private const val ENTER_ONLY_AVAILABLE_REPLIES = "대답은 y(예), n(아니오)로만 할 수 있습니다"
private const val ENTER_BET_MONEY = "의 배팅 금액은?"
private const val MINIMUM_BET_MONEY = 10
private const val RE_ENTER_BET_MONEY = "${MINIMUM_BET_MONEY}원 이상 입력해주세요"
val POSSIBLE_REPLIES: List<String> = listOf("y", "n")

object InputView {

    fun readPlayerNames(): String = readText(message = ENTER_PLAYER_NAMES)

    fun readBetMoney(name: String): Int = readNumber("\n${name}$ENTER_BET_MONEY?")

    fun readReplyToHit(player: Player): String {
        var text = readText(message = "${player}는(은) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        while (!POSSIBLE_REPLIES.contains(text)) {
            text = readText(message = ENTER_ONLY_AVAILABLE_REPLIES)
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

    private fun readNumber(message: String = ""): Int {
        println(message)
        var number = readLine()?.toIntOrNull()
        while (number == null || number < MINIMUM_BET_MONEY) {
            println(RE_ENTER_BET_MONEY)
            number = readLine()?.toIntOrNull()
        }
        return number
    }
}
