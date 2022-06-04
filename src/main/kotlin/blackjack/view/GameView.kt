package blackjack.view

import blackjack.domain.Participant
import blackjack.domain.BlackJackGame

object GameView {
    fun giveCard(participants: BlackJackGame) {
        participants.players.joinToString { it.name }.also {
            println("${it}에게 2장을 나눠주었습니다.")
        }
    }

    fun displayInitialCard(participants: BlackJackGame) {
        participants.players.forEach {
            println("${it.name}카드 : ${it.getCardDisplayName()}")
        }
    }

    fun displayPlayerCard(participant: Participant) {
        participant.also {
            println("${it.name}카드 : ${it.getCardDisplayName()}")
        }
    }

    fun displayResult(participants: BlackJackGame) {
        participants.players.forEach {
            print("${it.name}카드 : ${it.getCardDisplayName()}")
            print(" - ")
            println("결과 : ${it.score()}")
        }
    }
}
