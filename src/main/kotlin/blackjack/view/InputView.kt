package blackjack.view

import blackjack.model.PlayAnswer

object InputView {
    fun getPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun askGetCardMore(name: String): PlayAnswer {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln()
        require(answer == "y" || answer == "n") {
            "대답은 y나 n으로 하십사오"
        }
        return PlayAnswer.getKey(answer)
    }
}
