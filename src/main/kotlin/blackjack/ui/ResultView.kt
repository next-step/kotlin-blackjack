package blackjack.ui

class ResultView(val outputProvider: (String) -> Unit = { println(it) }) {
    fun printScoreResult(roundResults: List<RoundResult>) {
        roundResults.forEach { (userName, cards, score) ->
            printScoreResult(userName, cards, score)
        }
    }

    private fun printScoreResult(
        name: UserName,
        cards: UserCards,
        score: Score,
    ) {
        outputProvider("${name}카드: ${cards.toPrettyString()} - 결과: $score")
    }

    fun printUserCardCount(
        dealerName: UserName,
        userNames: UserNames,
        count: Int,
    ) {
        outputProvider("${dealerName}와 ${userNames.joinToString(", ")}에게 ${count}장의 나누었습니다.")
    }

    fun printUserCards(roundResults: List<RoundResult>) {
        roundResults.forEach { (name, cards) ->
            outputProvider("${name}카드: ${cards.toPrettyString()}")
        }
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

    fun printRound(
        name: UserName,
        cards: UserCards,
    ) {
        outputProvider("${name}카드: ${cards.toPrettyString()}")
    }

    fun printDealerTurnStart(dealerHitScore: Int) {
        outputProvider("딜러는 ${dealerHitScore}점 이하라 한장의 카드를 더 받았습니다.")
    }
}
