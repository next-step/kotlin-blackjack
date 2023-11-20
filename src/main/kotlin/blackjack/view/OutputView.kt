package blackjack.view

object OutputView {
    fun printCardDealingHeader(
        nicknames: String, numOfCards: Int
    ) {
        println("\n${nicknames}에게 ${numOfCards}장의 나누었습니다.")
    }

    fun printCardDealing(nickname: String, cards: String) {
        println("${nickname}카드: $cards")
    }

    fun printGameScore(
        nickname: String,
        cards: String,
        score: Int
    ) {
        println("${nickname}카드: $cards - 결과: $score")
    }
}
