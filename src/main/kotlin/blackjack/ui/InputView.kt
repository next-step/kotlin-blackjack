package blackjack.ui

import blackjack.dto.GeneratePlayerRequest

class InputView {
    fun getPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNameText = readln()
        return parsePlayerName(playerNameText)
    }

    fun getPlayerRequest(playerNameList: List<String>): List<GeneratePlayerRequest> {
        return playerNameList.map {
            println("${it}의 베팅 금액은?")
            val bettingMoney = readln().toInt()
            println()
            GeneratePlayerRequest(it, bettingMoney)
        }
    }

    private fun parsePlayerName(playerNameText: String): List<String> {
        return playerNameText.split(",").toList()
    }

    fun askPlayersWantToDrawCard(): Boolean {
        val playerAnswer = readln()
        return playerAnswer.lowercase() == "y"
    }
}
