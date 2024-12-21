package blackjack.view

object InputView {
    fun readNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readLine()?.trim() ?: throw IllegalArgumentException("입력이 없습니다.")
        require(input.isNotEmpty()) { "이름은 최소 1개 이상 입력해야 합니다." }
        return input.split(",").map { it.trim() }.also {
            require(it.all { name -> name.isNotEmpty() }) { "빈 이름은 입력할 수 없습니다." }
        }
    }

    fun readMoreCard(name: String): Boolean {
        while (true) {
            println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            when (readLine()?.trim()?.lowercase()) {
                "y" -> return true
                "n" -> return false
                else -> println("y 또는 n만 입력해주세요.")
            }
        }
    }
}
