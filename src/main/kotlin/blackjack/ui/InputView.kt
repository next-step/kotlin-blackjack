package blackjack.ui

object InputView {

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요. (쉽표 기준으로 분리")
        val names = readLine()
        return names?.split(",") ?: throw IllegalArgumentException()
    }

    fun selectDrawCard(name: String): String {
        println("${name}은 한장의 카드를 더 받겠습니까?")
        return readLine() ?: throw IllegalArgumentException("Y/N 으로 대답해주세요")
    }

    fun inputBatting(names: List<String>): List<Double> {
        val bettingTable = mutableListOf<Double>()
        for (name in names) {
            println("${name}의 배팅 금액은 ?")
            val money = readLine()?.toDouble() ?: throw IllegalArgumentException("돈은 공백일 수 없습니다.")
            bettingTable.add(money)
        }
        return bettingTable
    }
}
