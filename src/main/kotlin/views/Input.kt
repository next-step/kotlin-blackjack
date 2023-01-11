package views

object Input {
    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()?.split(",") ?: throw IllegalArgumentException("올바른 양식이 아닙니다")
    }

    fun answerDrawCard(playerName: String): Boolean {
        println("$playerName 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readLine()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("올바른 양식이 아닙니다")
        }
    }
}
