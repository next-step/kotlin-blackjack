package blackjack.ui

typealias CardRank = String
typealias CardSuit = String
typealias UserCards = Map<CardRank, List<CardSuit>>
typealias Score = Int
typealias RoundResult = Map<UserName, UserCards>
typealias ViewResult = Map<UserName, Map<UserCards, Score>>
typealias ViewSuits = Map<String, String>

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

    companion object {
        private val VIEW_SUIT: ViewSuits =
            mapOf(
                "HEART" to "하트",
                "DIAMOND" to "다이아몬드",
                "CLUB" to "클로버",
                "SPADE" to "스페이드",
            )

        fun UserCards.toPrettyString(): String {
            return this.entries.joinToString(separator = ", ") { (rank, suits) ->
                suits.joinToString(separator = ", ") { "$rank${VIEW_SUIT[it]}" }
            }
        }
    }
}
