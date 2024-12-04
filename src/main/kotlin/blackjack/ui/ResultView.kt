package blackjack.ui

class ResultView(val outputProvider: (String) -> Unit = { println(it) }) {
    fun printUserCardCount(
        names: UserNames,
        count: Int,
    ) {
        outputProvider("${names.joinToString(", ")}에게 ${count}장의 나누었습니다.")
    }

    fun printUserCards(users: RoundResult) {
        users.forEach { (name, cards) ->
            printRound(name, cards)
        }
    }

    fun printRound(
        name: UserName,
        cards: UserCards,
    ) {
        outputProvider("${name}카드: ${cards.toPrettyString()}")
    }

    fun printResult(result: ViewResult) {
        result.forEach { (userName, cards) ->
            printCards(cards, userName)
        }
    }

    private fun printResult(
        name: UserName,
        cards: UserCards,
        score: Score,
    ) {
        outputProvider("${name}카드: ${cards.toPrettyString()} - 결과: $score")
    }

    private fun printCards(
        cards: Map<UserCards, Score>,
        userName: UserName,
    ) {
        cards.forEach { (cards, score) ->
            printResult(userName, cards, score)
        }
    }
}
