package view

import java.util.Locale

class ConsoleInputView : InputView {
    override fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()?.split(",")?.map { it.trim() } ?: listOf()
    }

    override fun askForAnotherCard(playerName: String): Boolean {
        println("${playerName}은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readlnOrNull()?.lowercase(Locale.getDefault()) == "y"
    }

    override fun readBettingAmount(playerName: String): Int {
        println("${playerName}의 배팅 금액은?")
        return readln().toInt()
    }
}
