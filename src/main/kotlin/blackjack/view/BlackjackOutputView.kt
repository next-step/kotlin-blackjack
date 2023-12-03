package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Participant
import blackjack.domain.Participants
import blackjack.domain.PlayerResult
import blackjack.domain.GameResult
import blackjack.domain.Suit

object BlackjackOutputView {
    fun printInitialCards(participants: Participants) {
        println("\n${participants.value.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")

        participants.value.forEach { printCards(it) }
        println()
    }

    fun printCards(participant: Participant) {
        println("${participant.name} 카드: ${getCardsString(participant)}")
    }

    fun printDealerReceiveCard(dealer: Dealer) {
        println("\n${dealer.name}는 ${Game.DEALER_RECEIVE_CARD_SCORE}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printCardResult(participants: Participants) {
        println()

        participants.value.forEach {
            println("${it.name} 카드: ${getCardsString(it)} - 결과: ${it.cards.calculateScore()}")
        }
    }

    fun printGameResult(participants: Participants, gameResult: GameResult) {
        println("\n## 최종 승패")
        printDealerResult(participants.dealer, gameResult)
        printPlayerResults(participants, gameResult)
    }

    private fun getCardsString(participant: Participant): String {
        return participant.cards
            .get()
            .joinToString { it.toOutputString() }
    }

    private fun printDealerResult(dealer: Dealer, gameResult: GameResult) {
        val dealerResult = buildString {
            gameResult.getDealerResult()
                .forEach { (playerResult, count) ->
                    append("$count${playerResult.toOutputString()} ")
                }
        }
        println("${dealer.name}: $dealerResult")
    }

    private fun printPlayerResults(participants: Participants, gameResult: GameResult) {
        participants.players
            .zip(gameResult.playerResults)
            .forEach { (player, playerResult) ->
                println("${player.name}: ${playerResult.toOutputString()}")
            }
    }

    private fun Card.toOutputString(): String {
        return "${this.denomination.alias}${this.suit.toOutputString()}"
    }

    private fun Suit.toOutputString(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    private fun PlayerResult.toOutputString(): String {
        return when (this) {
            PlayerResult.WIN -> "승"
            PlayerResult.DRAW -> "무"
            PlayerResult.LOSE -> "패"
        }
    }
}
