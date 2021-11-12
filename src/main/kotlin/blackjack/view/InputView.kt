package blackjack.view

class InputView {
    fun inputPlayers(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readLine() ?: DEFAULT_INPUT
    }

    companion object {
        private const val DEFAULT_INPUT = ""
    }
}
