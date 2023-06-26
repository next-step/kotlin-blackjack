package blackjack.view

object InputView {
    fun getPlayersList(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").toList().map { it.trim() }
    }

    fun isHit(name: String): Boolean {
        println("$name 은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        var userInput = readln()

        while (userInput != "y" && userInput != "n") {
            println("예는 y, 아니오는 n 만 입력할 수 있습니다.")
            userInput = readln()
        }

        return userInput == "y"
    }
}
