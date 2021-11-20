package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

object OutputView {
    fun printStartResult(participants: Participants) {
        val allPlayers = participants.getAllPlayers()

        val names = allPlayers.joinToString { player -> player.name }
        println("$names 에게 2장의 카드를 나누어주었습니다.")

        allPlayers.forEach { player ->
            printPlayerCard(player)
        }
    }

    fun printPlayerCard(player: Participant) {
        val name = player.name
        val cardNames = player.cards.map { card ->
            "${card.denomination.denomination} ${card.pattern}"
        }

        println("$name 카드: $cardNames")
    }

    fun printDivideResult(participants: Participants) {
        participants
            .getAllPlayers()
            .forEach { player ->
                val cardNames = player.cards.map { card ->
                    "${card.denomination.denomination} ${card.pattern}"
                }

                println("${player.name}카드 $cardNames 합계: ${player.getCardSum()}")
            }
    }

    fun printMatchResult(participants: Participants) {
        println("### 최종 수익")

        participants
            .getAllPlayers()
            .forEach { participant ->
                println("${participant.name}: ${participant.getRevenue()}")
            }
    }

    fun printDealerReceivedCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
