package blackjack.ui

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Players

object OutputView {
    fun printAllPlayerCards(players: Players, dealer: Dealer) {
        val names = players.values.joinToString { it.name }
        println("딜러와 ${names} 에게 2장의 카드를 나누었습니다.")
        for(participant in players.values) {
            println("${participant.name} 카드 : ${participant.showCards()}")
        }
        println("${dealer.name} 카드 : ${dealer.showCards()}")
    }

    fun printPlayerCards(participant: Participant) {
        println("${participant.name} 의 카드 : ${participant.showCards()}")
    }

    fun printResult(players: Players, dealer: Dealer) {
        println("\n ----------- 결산 -----------")
        for (participant in players.values) {
            println("${participant.name} 카드 : ${participant.showCards()} - 합계 ${participant.getScore()}")
        }
        println("${dealer.name} 카드 : ${dealer.showCards()} - 합계 ${dealer.getScore()}")
    }

    fun printDealerDrawInfo(dealer: Dealer) {
        if(dealer.checkCardDrawAvailable()){
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            return
        }
        println("딜러는 17이상이라 카드를 더이상 받지 않습니다.")
    }

    fun printGameWinning(winningPlayers: Map<String, Boolean>, dealer:Dealer) {
        println("딜러 : ${winningPlayers.count{ !it.value }}승 ${winningPlayers.count{ it.value }}패")
        winningPlayers.forEach { name, isWinner ->
            println("${name} : ${convertBooleanToString(isWinner)}")
        }
    }

    private fun convertBooleanToString(boolean: Boolean): String {
        if(boolean) return "승"
        return "패"
    }
}