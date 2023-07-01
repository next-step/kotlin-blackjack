package blackjack.view

object InputView {
    fun inputNames(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln()
        println()
        return names
    }

    fun inputBatting(name: String): Int {
        println("${name}의 배팅 금액은?")
        var batting = readln().toIntOrNull()

        while (batting == null) {
            println("입력 값이 잘못되었습니다")
            batting = readln().toIntOrNull()
        }
        println()
        return batting
    }

    fun inputCard(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        var input = readln()
        while (input !in listOf("y", "n")) {
            println("입력 값이 잘못되었습니다")
            input = readln()
        }
        return input == "y"
    }
}
