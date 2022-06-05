package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Participant

object GameView {
    fun giveCard(participants: BlackJackGame) {
        participants.players.joinToString { it.name }.also {
            println("${it}에게 2장을 나눠주었습니다.")
        }
    }

    fun displayInitialCard(participants: BlackJackGame) {
        participants.players.forEach {
            println("${it.name}카드 : ${getCardDisplayName(it)}")
        }
    }

    fun displayPlayerCard(participant: Participant) {
        participant.also {
            println("${it.name}카드 : ${getCardDisplayName(it)}")
        }
    }

    fun displayResult(participants: BlackJackGame) {
        participants.players.forEach {
            print("${it.name}카드 : ${getCardDisplayName(it)}")
            print(" - ")
            println("결과 : ${it.score()}")
        }
    }

    private fun getCardDisplayName(player: Participant): String {
        return player.cards.joinToString { "${it.denomination.displayName}${it.pattern.displayName}" }
    }
}
