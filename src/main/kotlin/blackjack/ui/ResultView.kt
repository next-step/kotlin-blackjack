package blackjack.ui

class ResultView(val outputProvider: (String) -> Unit = { println(it) }) {
    fun printUserCardCount(
        dealerName: Name,
        userNames: UserNames,
        count: Int,
    ) {
        outputProvider("${dealerName}와 ${userNames.joinToString(", ")}에게 ${count}장의 나누었습니다.")
    }

    fun printUserCards(users: RoundResult) {
        users.forEach { (name, cards) ->
            printRound(name, cards)
        }
    }

    fun printRound(
        name: Name,
        cards: UserCards,
    ) {
        outputProvider("${name}카드: ${cards.toPrettyString()}")
    }

    fun printResult(
        dealerResults: ViewResult,
        finalRoundResults: ViewResult,
    ) {
        printResult(dealerResults)
        printResult(finalRoundResults)
    }

    fun printResult(result: ViewResult) {
        result.forEach { (userName, cards) ->
            printCards(cards, userName)
        }
    }

    fun printDealerTurnStart(dealerHitScore: Int) {
        outputProvider("딜러는 ${dealerHitScore}점 이하라 한장의 카드를 더 받았습니다.")
    }

    fun printFinalWinner(finalWinnerResults: FinalWinnerResults) {
        outputProvider("\n## 최종 승패")
        outputProvider(
            "딜러: ${finalWinnerResults.dealerResult.wins}${UIMatchType.WIN.displayName} ${finalWinnerResults.dealerResult.losses}${UIMatchType.LOSS.displayName} ${finalWinnerResults.dealerResult.draws}${UIMatchType.DRAW.displayName}",
        )
        finalWinnerResults.playerResults.forEach { (name, result) ->
            outputProvider("$name: ${result.displayName}")
        }
    }

    private fun printResult(
        name: Name,
        cards: UserCards,
        score: Score,
    ) {
        outputProvider("${name}카드: ${cards.toPrettyString()} - 결과: $score")
    }

    private fun printCards(
        cards: Map<UserCards, Score>,
        userName: Name,
    ) {
        cards.forEach { (cards, score) ->
            printResult(userName, cards, score)
        }
    }
}
