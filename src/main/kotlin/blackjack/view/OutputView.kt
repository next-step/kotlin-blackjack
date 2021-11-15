package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.Players
import blackjack.domain.player.ResultStatus

object OutputView {
    fun printStartResult(players: Players) {
        val allPlayers = players.getAllPlayers()

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

    fun printDivideResult(players: Players) {
        players
            .getAllPlayers()
            .forEach { player ->
                val cardNames = player.cards.map { card ->
                    "${card.denomination.denomination} ${card.pattern}"
                }

                println("${player.name}카드 $cardNames 합계: ${player.getCardSum()}")
            }
    }

    fun printMatchResult(players: Players) {
        val dealer = players.dealer
        val guest = players.guest

        val dealerMatchResult = dealer.getMatchResult()

        println("${dealer.name}: ${dealerMatchResult[ResultStatus.WIN] ?: 0}승 ${dealerMatchResult[ResultStatus.LOSE]}패 ${dealerMatchResult[ResultStatus.TIE] ?: 0}무")

        guest.forEach { player ->
            println("${player.name}: ${player.resultStatus.value}")
        }
    }
}
