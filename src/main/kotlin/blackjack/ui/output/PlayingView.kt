package blackjack.ui.output

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant

object PlayingView {

    fun showDistribution(dealer: Dealer, players: List<Participant>) {
        val names = getNames(players)
        println("${dealer.name}와 ${names}에게 2장의 카드를 나누었습니다.")
        showOpenHand(dealer)
        players.forEach {
            showOpenHand(it)
        }
    }

    private fun getNames(players: List<Participant>): String {
        return players.joinToString { it.name }
    }

    fun showOpenHand(participant: Participant) {
        println("${participant.name}카드: ${participant.open()}")
    }

    fun showDealerHit() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }
}
