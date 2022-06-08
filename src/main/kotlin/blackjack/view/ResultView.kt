package blackjack.view

class ResultView {

    fun players(players: List<String>) {
        println(players)
        println("${players.joinToString(", ")} 에게 2장의 나누었습니다.")
    }
}
