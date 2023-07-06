package blackjack.view

object InputView {
    fun inputPlayersNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(',')
    }

    fun inputBettingMoney(names: List<String>): List<Int> {
        val moneys = buildList {
            names.forEach {
                println("${it}의 베팅 금액은?")
                add(readln().toInt())
            }
        }
        return moneys
    }
}
