package blackjack.view

object ConsoleInputView {

    fun getNames(): String {
        return getInput("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun getAnswer(name: String): String {
        println()
        return getInput("${name}은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    private fun getInput(message: String): String {
        println(message)
        return readLine()!!
    }
}
