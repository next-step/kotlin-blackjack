package blackjack.view

object InputView {
    fun getNicknames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val nicknames = readlnOrNull() ?: throw IllegalArgumentException("이름을 입력해주세요.")
        return nicknames.split(",").map { it.trim() }
    }

    fun askWillHit(nickname: String): Boolean {
        println("${nickname}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln().trim()
        require(answer.isNotBlank()) { "y 또는 n을 입력해주세요." }
        require(answer == "y" || answer == "n") { "y 또는 n을 입력해주세요." }
        return answer == "y"
    }
}
