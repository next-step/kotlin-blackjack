package blackjack

object InputView {

    fun requestPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine()?.split(",") ?: emptyList()
    }

    fun requestGambleMoney(players: List<Player>): List<Int> {
        val gambleMoneyPerPlayer = mutableListOf<Int>()
        players.map {
            println("${it}의 배팅 금액은?")
            gambleMoneyPerPlayer.add(readLine()?.toInt() ?: 0)
        }
        return gambleMoneyPerPlayer
    }
}
