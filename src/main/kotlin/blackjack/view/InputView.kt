package blackjack.view

object InputView {
    fun player(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.")
        return readLine()?.split(",") ?: throw IllegalArgumentException()
    }

    fun getMoreCard(name: String): Boolean {
        println("${name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readLine() ?: throw IllegalArgumentException()
        return input == "y"
    }
}
