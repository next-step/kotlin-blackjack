package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.Drawable
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.exception.InvalidInputValueException

object GameView : Drawable {
    fun drawFirstCardDistribution(participants: BlackJackGame) {
        participants.participants.joinToString { it.name }.also {
            println("${it}에게 2장을 나눠주었습니다.")
        }
    }

    fun displayInitialCard(participants: BlackJackGame) {
        participants.participants.forEach {
            println("${it.name}카드 : ${getCardDisplayName(it)}")
        }
    }

    fun printDrawResult(participant: Participant) {
        if (participant.isDealer()) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("${participant.name}카드 : ${getCardDisplayName(participant)}")
        }
    }

    fun displayResult(blackJackGame: BlackJackGame) {
        println()
        blackJackGame.participants.forEach {
            print("${it.name}카드 : ${getCardDisplayName(it)}")
            print(" - ")
            println("결과 : ${it.participantCards.score()}")
        }
    }

    private fun getCardDisplayName(participant: Participant): String {
        return participant.participantCards.playerCards.joinToString { "${it.denomination.displayName}${it.pattern.toDisplayName()}" }
    }

    private fun Card.CardPattern.toDisplayName(): String {
        return when (this) {
            Card.CardPattern.CLUBS -> "클로버"
            Card.CardPattern.SPADES -> "스페이드"
            Card.CardPattern.HEARTS -> "하트"
            Card.CardPattern.DIAMONDS -> "다이아몬드"
        }
    }

    override fun canDraw(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().toBoolean().also {
            if (it) player.setBlackJackStatusStay()
        }
    }

    private fun String.toBoolean(): Boolean {
        return when (uppercase()) {
            YES -> true
            NO -> false
            else -> throw InvalidInputValueException()
        }
    }

    private const val YES = "Y"
    private const val NO = "N"
}
