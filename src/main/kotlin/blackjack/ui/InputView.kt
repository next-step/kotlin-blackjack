package blackjack.ui

import blackjack.domain.Player

class InputView {
    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNameText = readln()
        return parsePlayerName(playerNameText)
    }

    private fun parsePlayerName(playerNameText: String): List<String> {
        return playerNameText.split(",").toList()
    }

}