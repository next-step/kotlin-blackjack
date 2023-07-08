package baclkjack.view

object InputView {
    private const val DELIMITER = ","
    fun inputPlayer(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(DELIMITER)
    }

    fun inputCardDraw(player: String): String {
        println("$player 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val yn = readln()
        require(yn == "y" || yn == "n") {
            "y,n 입력 가능 합니다."
        }
        return yn
    }

    fun inputBetting(player: String): Int {
        println("${player}의 배팅 금액은? ")
        return readln().toInt()
    }
}
