package interfaces.ui

object InputConsole {

    private const val NAME_DELIMITER = ","

    fun queryParticipantName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().trim()
        return names.split(NAME_DELIMITER).map { it.trim() }
    }

    fun queryReceiveCard(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return queryReceiveCard()
    }

    private fun queryReceiveCard(): Boolean {
        val isReceiveCardString = readln().trim()
        if (isReceiveCardString == "y") {
            return true
        } else if (isReceiveCardString == "n") {
            return false
        }
        throw RuntimeException("")
    }
}
