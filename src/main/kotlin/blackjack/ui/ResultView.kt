package blackjack.ui

class ResultView(val outputProvider: (String) -> Unit = { println(it) }) {
    fun printUserCardCount(
        names: UserNames,
        count: Int,
    ) {
        outputProvider("${names.joinToString(", ")}에게 ${count}장의 나누었습니다.")
    }

    fun printUserCards(
        name: UserName,
        cards: UserCards,
    ) {
        outputProvider("${name}카드: ${cards.joinToString(", ")}")
    }

    fun printResult(
        name: UserName,
        cards: UserCards,
        score: Score,
    ) {
        outputProvider("${name}카드: ${cards.joinToString(", ")} - 결과: $score")
    }

    fun printUserCardsOfMap(users: Result) {
        users.forEach { (name, cards) ->
            printUserCards(name, cards)
        }
    }
}
