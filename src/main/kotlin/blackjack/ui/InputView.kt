package blackjack.ui

object InputView {

    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun requestCard(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val request = readln()
        validateRequest(request)
        return request == "y"
    }

    private fun validateRequest(request: String) {
        if (request != "y" && request != "n") {
            throw IllegalArgumentException("y 또는 n만 입력 가능합니다.")
        }
    }

    fun getBettingMoney(name: String): Long {
        println("${name}의 배팅 금액은?")
        return readln().toLong()
    }
}
