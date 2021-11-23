package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

object OutputView {
    fun printStartResult(participants: Participants) {
        val receiveCardStatisticsCollection = participants.getReceiveCardStatistics()

        val names = receiveCardStatisticsCollection.joinToString { receiveCardStatistics ->
            receiveCardStatistics.playerName
        }

        println("$names 에게 2장의 카드를 나누어주었습니다.")

        receiveCardStatisticsCollection.forEach { receiveCardStatistics ->
            println("${receiveCardStatistics.playerName}: ${receiveCardStatistics.cardNames}")
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
            .getReceiveCardStatistics()
            .forEach { player ->
                println("${player.playerName}카드 ${player.cardNames} 합계: ${player.cardSum}")
            }
    }

    fun printMatchResult(participants: Participants) {
        println("### 최종 수익")

        participants
            .getRevenueStatistics()
            .forEach { participant ->
                println("${participant.playerName}: ${participant.revenue}")
            }
    }

    fun printDealerReceivedCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}
