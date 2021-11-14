package blackjack.view

object InputView {

    private const val DEFAULT_INPUT = ""

    fun inputPlayersName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readLine() ?: DEFAULT_INPUT
    }

    fun inputWantMoreCard(name: String): String {
        println("${name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return readLine() ?: DEFAULT_INPUT
    }
}
