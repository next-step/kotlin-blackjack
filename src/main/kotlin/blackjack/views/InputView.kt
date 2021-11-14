package blackjack.views

object InputView {

    tailrec fun askGamerNames(): String {
        println(PLAYER_NAMES_QUESTION)
        return readLine() ?: askGamerNames()
    }

    private const val PLAYER_NAMES_QUESTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
}
