package blackjack.views

object InputView {

    tailrec fun askGamerNames(): String {
        println(PLAYER_NAMES_QUESTION)
        return readLine() ?: askGamerNames()
    }

    tailrec fun askGamerReceiveMoreCard(name: String): String {
        println("$name$RECEIVE_MORE_CARD_QUESTION")
        val readLine = readLine()
        if (readLine.isNullOrBlank() || (readLine != "y" && readLine != "n")) {
            return askGamerReceiveMoreCard(name)
        }
        return readLine
    }

    private const val PLAYER_NAMES_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val RECEIVE_MORE_CARD_QUESTION = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}
