package blackjack.step2.view

import blackjack.step2.domain.Card
import blackjack.step2.domain.GameResult
import blackjack.step2.domain.GameResultType
import blackjack.step2.domain.Participant

object OutputView {
    fun printCardDealingIntro(playerNames: List<String>) {
        println("딜러와 ${playerNames.joinToString(", ")}에게 2장의 카드를 나누었습니다.")
    }

    fun printFirstDealtCard(participants: List<Participant>) {
        participants.forEach { participant ->
            println("${participant.name} 카드: ${formatCards(participant.cards.all)}")
        }
    }

    fun printCardResult(participants: List<Participant>) {
        participants.forEach { participant ->
            println(
                "${participant.name} 카드: ${formatCards(participant.cards.all)} (결과: ${participant.calculateScore()})",
            )
        }
    }

    fun printFinalResults(results: List<GameResult>) {
        println("## 최종 승패")
        results.forEach { result ->
            val resultText =
                if (result.participant.name == "딜러") {
                    // 딜러의 결과를 출력
                    val winCount = result.resultTypes.count { it == GameResultType.WIN }
                    val loseCount = result.resultTypes.count { it == GameResultType.LOSE }
                    val drawCount = result.resultTypes.count { it == GameResultType.DRAW }
                    "${result.participant.name}: ${winCount}승 ${drawCount}무 ${loseCount}패"
                } else {
                    // 플레이어의 결과를 출력
                    val resultType = result.resultTypes.firstOrNull() ?: GameResultType.DRAW
                    val resultTypeText =
                        when (resultType) {
                            GameResultType.WIN -> "승"
                            GameResultType.LOSE -> "패"
                            GameResultType.DRAW -> "무승부"
                        }
                    "${result.participant.name}: $resultTypeText"
                }
            println(resultText)
        }
    }

    private fun formatCards(cards: List<Card>): String {
        return cards.joinToString(", ") {
            "${it.number.denomination}${it.type.value}"
        }
    }
}
