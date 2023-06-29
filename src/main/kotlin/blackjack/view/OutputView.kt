package blackjack.view

import blackjack.model.BlackjackDealerMoreCardScoreLimitConsumer
import blackjack.model.BlackjackJudgeConsumer
import blackjack.model.BlackjackPlayerConsumer
import blackjack.model.BlackjackPlayersCardCountConsumer
import blackjack.model.BlackjackPlayersScoreConsumer
import blackjack.model.BlackjackRevenueJudge
import blackjack.model.HandDeck
import blackjack.model.TrumpCard
import blackjack.model.TrumpCardNumber
import blackjack.model.TrumpCardShape
import blackjack.model.participant.BlackjackDealer
import blackjack.model.participant.BlackjackParticipant
import blackjack.model.participant.BlackjackPlayer

object OutputView :
    BlackjackDealerMoreCardScoreLimitConsumer,
    BlackjackJudgeConsumer,
    BlackjackPlayerConsumer,
    BlackjackPlayersCardCountConsumer,
    BlackjackPlayersScoreConsumer {

    override fun consumeDealerMoreCardScoreLimit(scoreLimit: Int) {
        println("딜러는 ${scoreLimit}이하라 한장의 카드를 더 받았습니다.")
    }

    override fun consume(blackjackRevenueJudge: BlackjackRevenueJudge) {
        println("## 최종 수익")
        println("딜러: ${blackjackRevenueJudge.dealerRevenue}")
        blackjackRevenueJudge.playerResults.forEach {
            println("${it.player.name}: ${it.revenue}")
        }
    }

    override fun consumeParticipant(participant: BlackjackParticipant) {
        println(getParticipantString(participant))
    }

    override fun consumePlayersCardCount(
        dealer: BlackjackDealer,
        players: Collection<BlackjackPlayer>,
        cardCount: Int,
    ) {
        println("딜러와 ${players.joinToString { it.name.toString() }}에게 ${cardCount}장의 나누었습니다.")
        println("딜러 : ${deckString(dealer.handDeck)}")
        players.forEach { consumeParticipant(it) }
        println()
    }

    override fun consumePlayers(dealer: BlackjackDealer, players: Collection<BlackjackPlayer>) {
        (listOf(dealer) + players).forEach {
            println("${getParticipantString(it)} - 결과: ${it.deckScore}")
        }
        println()
    }

    private fun getParticipantString(participant: BlackjackParticipant): String {
        return when (participant) {
            is BlackjackDealer -> "딜러 카드: ${deckString(participant.handDeck)}"
            is BlackjackPlayer -> "${participant.name} 카드: ${deckString(participant.handDeck)}"
        }
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
