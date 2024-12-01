package blackjack.ui

class ResultView(val outputProvider: (String) -> Unit = { println(it) }) {
    fun printUserCardCount(names: List<String>, count: Int) {
        outputProvider("${names.joinToString(", ")}에게 ${count}장의 나누었습니다.")
    }

    fun printUserCards(name: String, cards: List<String>) {
        outputProvider("${name}카드: ${cards.joinToString(", ")}")
    }

    fun printResult(name: String, cards: List<String>, score: Int) {
        outputProvider("${name}카드: ${cards.joinToString(", ")} - 결과: ${score}")
    }

    fun printUserCardsOfMap(users: Map<String, List<String>>) {
        users.forEach { (name, cards) ->
            printUserCards(name, cards)
        }
    }
}
