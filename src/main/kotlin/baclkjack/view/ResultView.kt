package baclkjack.view

object ResultView {
    fun showHit(names: String) {
        println("$names 에게 2장의 나누었습니다.")
    }

    fun showPlayerCard(name: String, cards: String) {
        println("${name}카드: $cards")
    }

    fun showPlayerResult(name: String, cards: String, score: Int) {
        println("${name}카드: $cards - 결과 : $score")
    }
}
