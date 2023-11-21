package blackjack.view

import blackjack.domain.*

object OutputView {

    private const val SEPARATOR = ", "

    fun writePlayerNames(participants: List<Participant>) {
        println(
            "딜러와 ${
                participants.filterIsInstance<Player>().joinToString(SEPARATOR) { it.name.value }
            }에게 2장의 나누었습니다."
        )
    }

    fun writeParticipantCards(participants: List<Participant>) {
        participants.forEach {
            println(
                "${it.name.value}카드: ${
                    it.cards.joinToString(SEPARATOR) { card ->
                        "${card.rank.name()}${card.symbol.name()}"
                    }
                }"
            )
        }
        println()
    }

    fun writeDealer(dealer: Dealer) {
        println("${dealer.name.value}는 16이하라 한 장의 카드를 더 받았습니다.")
        println()
    }

    fun writeParticipantResults(participants: List<Participant>) {
        participants.forEach { participant ->
            println(
                "${participant.name.value}카드: ${
                    participant.cards.joinToString(SEPARATOR) { card ->
                        "${card.rank.name()}${card.symbol.name()}"
                    }
                } - 결과: ${participant.calculateScore()}"
            )
        }
        println()
    }

    fun writeGameResults(result: GameResult) {
        println("## 최송 승패")
        println("딜러: ${result.dealerStats.wins}승 ${result.dealerStats.losses}패")
        result.playerResults.forEach { (player, outcome) ->
            println("${player.name.value}: ${outcome.name()}")
        }
    }

    private fun Rank.name(): String {
        return when (this) {
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
            Rank.ACE -> "A"
            else -> this.score.toString()
        }
    }

    private fun Symbol.name(): String {
        return when (this) {
            Symbol.SPADE -> "스페이드"
            Symbol.HEART -> "하트"
            Symbol.DIAMOND -> "다이아"
            Symbol.CLUB -> "클로버"
        }
    }

    private fun GameOutcome.name(): String {
        return when (this) {
            GameOutcome.WIN -> "승"
            GameOutcome.LOSE -> "패"
        }
    }
}
