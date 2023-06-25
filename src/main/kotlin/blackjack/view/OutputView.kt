package blackjack.view

import blackjack.model.BlackjackDealer
import blackjack.model.BlackjackJudge
import blackjack.model.BlackjackParticipant
import blackjack.model.BlackjackPlayer
import blackjack.model.HandDeck
import blackjack.model.TrumpCard
import blackjack.model.TrumpCardNumber
import blackjack.model.TrumpCardShape

object OutputView {

    fun consumePlayersCardCount(dealer: BlackjackDealer, players: Collection<BlackjackPlayer>, cardCount: Int) {
        println("${dealer.name}와 ${players.joinToString { it.name.toString() }}에게 ${cardCount}장의 나누었습니다.")
        println("${dealer.name}: ${deckString(dealer.handDeck)}")
        players.forEach { consumeParticipant(it) }
        println()
    }

    fun consumeBlackjackDealerMoreCardScoreLimit(scoreLimit: Int) {
        println("딜러는 ${scoreLimit}이하라 한장의 카드를 더 받았습니다.")
    }

    fun consumeParticipant(participant: BlackjackParticipant) {
        println(participantString(participant))
    }

    fun blackjackParticipantsScoreConsumer(dealer: BlackjackDealer, players: Collection<BlackjackPlayer>) {
        (listOf(dealer) + players).forEach {
            println("${participantString(it)} - 결과: ${it.deckScore}")
        }
        println()
    }

    fun blackjackJudgeConsumer(blackjackJudge: BlackjackJudge) {
        println("## 최종 승패")
        println("딜러: ${blackjackJudge.dealerWinCount}승 ${blackjackJudge.dealerLoseCount}패")
        blackjackJudge.playerResults.forEach {
            println(
                "${it.player.name}: ${
                if (it.isWin) {
                    "승"
                } else {
                    "패"
                }
                }"
            )
        }
    }

    private fun participantString(participant: BlackjackParticipant): String {
        return "${participant.name} 카드: ${deckString(participant.handDeck)}"
    }

    private fun deckString(deck: HandDeck): String {
        return deck.cards.joinToString { cardString(it) }
    }

    private fun cardString(card: TrumpCard): String {
        return "${cardNumberString(card.number)}${cardShapeString(card.shape)}"
    }

    private fun cardShapeString(shape: TrumpCardShape) = when (shape) {
        TrumpCardShape.SPADE -> "스페이드"
        TrumpCardShape.HEART -> "하트"
        TrumpCardShape.DIAMOND -> "다이아몬드"
        TrumpCardShape.CLOVER -> "클로버"
    }

    private fun cardNumberString(number: TrumpCardNumber): String = when (number) {
        TrumpCardNumber.ACE -> "A"
        TrumpCardNumber.TWO -> "2"
        TrumpCardNumber.THREE -> "3"
        TrumpCardNumber.FOUR -> "4"
        TrumpCardNumber.FIVE -> "5"
        TrumpCardNumber.SIX -> "6"
        TrumpCardNumber.SEVEN -> "7"
        TrumpCardNumber.EIGHT -> "8"
        TrumpCardNumber.NINE -> "9"
        TrumpCardNumber.TEN -> "10"
        TrumpCardNumber.JACK -> "J"
        TrumpCardNumber.QUEEN -> "Q"
        TrumpCardNumber.KING -> "K"
    }
}
