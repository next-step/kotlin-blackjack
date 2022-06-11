package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Participant

object OutputView {

    fun showDistribution(dealer: Dealer, players: List<Participant>) {
        val names = getNames(players)
        println("${dealer.name}와 ${names}에게 2장의 카드를 나누었습니다.")
        showHand(dealer)
        players.forEach {
            showHand(it)
        }
    }

    private fun getNames(players: List<Participant>): String {
        return players.joinToString { it.name }
    }

    fun showHand(participant: Participant) {
        println("${participant.name}카드: ${participant.open()}")
    }

    fun showDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showResult(participants: List<Participant>) {
        println()
        participants.forEach {
            println("${it.name}카드: ${it.hand} - 결과: ${it.hand.calculate()}")
        }
    }
}
