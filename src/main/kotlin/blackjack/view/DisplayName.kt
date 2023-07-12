package blackjack.view

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

object DisplayName {
    fun cardNumber(cardNumber: CardNumber) = when (cardNumber) {
        CardNumber.ACE -> "A"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
        else -> cardNumber.cardScore.primary.toString()
    }

    fun cardShape(cardShape: CardShape) = when (cardShape) {
        CardShape.CLUB -> "클로버"
        CardShape.DIAMOND -> "다이아"
        CardShape.HEART -> "하트"
        CardShape.SPADE -> "스페이드"
    }

    fun participant(participant: Participant) = when (participant) {
        is Player -> participant.name
        is Dealer -> "딜러"
    }
}
