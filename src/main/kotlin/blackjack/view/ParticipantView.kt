package blackjack.view

import blackjack.domain.game.Game.Companion.FIRST_DRAW_NUMBER
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

object ParticipantView {
    fun printInitialHand(dealer: Dealer, players: List<Player>) {
        println()
        println("딜러와 ${players.joinToString { it.name }} 에게 $FIRST_DRAW_NUMBER 장의 카드를 나누었습니다.")
        printParticipantInfo(dealer)
        players.forEach { printParticipantInfo(it) }
        println()
    }

    fun printParticipantInfo(participant: Participant) {
        println(parseParticipantInfoToString(participant))
    }

    fun printDealerDrawOneCard() {
        println("딜러가 가진 패의 합계가 16 이하라서 한 장의 카드를 더 받았습니다.")
    }

    fun parseParticipantInfoToString(participant: Participant): String {
        val cards = participant.hand.cards
        return "${participant.name} 카드: ${cards.joinToString { "[${it.suit.displayName}]${it.symbol.displayName}" }}"
    }
}
