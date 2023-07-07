package baclkjack.view

object ResultView {
    fun showHit(dealer: String, names: String) {
        println("$dealer 와 $names 에게 2장의 나누었습니다.")
    }

    fun showPlayerCard(name: String, cards: String) {
        println("$name 카드: $cards")
    }

    fun showPlayerResult(name: String, cards: String, score: Int) {
        println("$name 카드: $cards - 결과 : $score")
    }

    fun showDealerCard(name: String) {
        println("$name 는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showFinal() {
        println("##최종 승패")
    }

    fun showWinner(name: String, result: String) {
        println("$name : $result")
    }
}
