package blackjack.view

import blackjack.view.input.Input

class InputView(private val input: Input) {
    val players: List<String> by lazy { askPlayers() }

    private fun askPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return input.readPlayers().split(",").map { it.trim() }
    }

    fun hasNextCard(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val input = input.readHasNextCard()

        if (input == "y") {
            return true
        }

        if (input == "n") {
            return false
        }

        throw IllegalArgumentException("y 혹은 n으로 입력해주세요. 현재입력값=$input")
    }
}
