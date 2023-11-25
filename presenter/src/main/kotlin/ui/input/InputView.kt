package ui.input

class InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        input.ifBlank { throw IllegalArgumentException("한 명이라도 입력해야 합니다.") }
        return input.split(",").map { it.trim() }
    }

    fun askForAdditionalCard(playerName: String): Boolean {
        println("${playerName}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln()
        return input.equals("y", ignoreCase = true)
    }
}
