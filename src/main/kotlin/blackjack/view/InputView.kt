package blackjack.view

import blackjack.model.Players

object InputView {
    fun readPlayers(): Players {
        var input: String? = null
        while (input.isNullOrBlank()) {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            input = readLine()
        }

        return Players.Builder().playerNames(input.split(",")).build()
    }
}