package blackjack.view

object InputView {
    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun getBettingAmount(playerName: String): Int {
        println("$playerName 의 배팅 금액은?")
        return readlnOrNull()?.toIntOrNull() ?: 0
    }

    fun askToHit(playerName: String): Boolean {
        println("$playerName 은 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readlnOrNull()?.lowercase() == "y"
    }
}
