package blackjack.view

object InputView {
    private const val CONTINUE = "y"
    private const val BREAK = "n"

    fun playerNames(): String {
        return question("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun askIfPlayerWantToMoreCard(name: String): Boolean {
        val answer = question("$name 는 한장의 카드를 더 받겠습니까?(예는 $CONTINUE, 아니요는 $BREAK)").toLowerCase()

        if (answer == CONTINUE) {
            return true
        } else if (answer == BREAK) {
            return false
        }

        throw IllegalArgumentException("잘못된 입력값입니다.")
    }

    private tailrec fun question(question: String): String {
        println(question)

        return readLine() ?: question(question)
    }
}
