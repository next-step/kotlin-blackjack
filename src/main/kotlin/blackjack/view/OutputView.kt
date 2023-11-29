package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.GameOutcome
import blackjack.domain.GameResult
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Symbol
import blackjack.domain.getScore

object OutputView {

    private const val SEPARATOR = ", "

    fun writePlayerNames(participants: List<Participant>) {
        val nicknames = participants.filterIsInstance<Player>().joinToString(SEPARATOR) { it.name.value }
        println("딜러와 $nicknames 에게 2장의 나누었습니다.")
    }

    fun writeParticipantCards(participants: List<Participant>) {
        participants.forEach {
            val nickname = it.name.value
            val cards = it.cards.joinToString(SEPARATOR) { card ->
                "${card.rank.name()}${card.symbol.name()}"
            }
            println("${nickname}카드: $cards")
        }
        println()
    }

    fun writeDealer(dealer: Dealer) {
        println("${dealer.name.value}는 16이하라 한 장의 카드를 더 받았습니다.")
        println()
    }

    fun writeGameResults(participants: List<Participant>, result: GameResult) {
        participants.forEach { participant ->
            val nickname = participant.name.value
            val cards = participant.cards.joinToString(SEPARATOR) { card ->
                "${card.rank.name()}${card.symbol.name()}"
            }
            println("${nickname}카드: $cards - 결과: ${participant.getScore()}")
        }
        println()

        println("## 최송 수익")
        println("딜러: ${result.dealerResults.sumOf { it.getValue().toInt() }}")
        result.playerResults.forEach { (player, gameReward) ->
            println("${player.name.value}: ${gameReward.getValue().toInt()}")
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
