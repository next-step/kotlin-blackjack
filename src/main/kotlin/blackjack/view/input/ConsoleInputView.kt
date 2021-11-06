package blackjack.view.input

import blackjack.domain.Players

class ConsoleInputView : InputView {
    override fun getPlayers(): Players {
        println(ENTER_PLAYERS_NAMES_MESSAGE)
        return Players.from(readLine() ?: "")
    }

    companion object {
        private const val ENTER_PLAYERS_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
