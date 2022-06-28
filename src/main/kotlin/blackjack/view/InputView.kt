package blackjack.view

class InputView {

    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(DELIMITER)
    }

    fun receiveOneMoreCard(playerName: String): Boolean {
        println("${playerName}은(는) 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n만 입력 가능합니다.")
        }
    }

    companion object {
        private const val DELIMITER = ","
    }
}
