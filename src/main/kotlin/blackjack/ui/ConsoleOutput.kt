package blackjack.ui

import blackjack.Dealer
import blackjack.Participant
import blackjack.ParticipantProfit
import blackjack.Player

class ConsoleOutput {
    companion object {
        fun printShareInitialCardsToParticipants(
            dealer: Dealer,
            players: List<Player>,
        ) {
            println()
            println("${dealer.name}와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        }

        fun printAllParticipantsWithNameAndHand(participants: List<Participant>) {
            participants.forEach { participant ->
                printParticipantWithNameAndHand(participant)
            }
            println()
        }

        fun printParticipantWithNameAndHand(participant: Participant) {
            printPlayerNameAndPlayerHand(participant)
            println()
        }

        fun printAllParticipantsWithNameAndHandAndResult(participants: List<Participant>) {
            println()
            participants.forEach { participant ->
                printPlayerWithNameAndHandAndResult(participant)
            }
            println()
        }

        private fun printPlayerWithNameAndHandAndResult(participant: Participant) {
            printPlayerNameAndPlayerHand(participant)
            println(" - 결과: ${participant.sumOfHand()}")
        }

        private fun printPlayerNameAndPlayerHand(participant: Participant) {
            print("${participant.name} 카드: ")
            print(participant.hand.joinToString { "${it.number.face}${it.suit.koreanName}" })
        }

        fun printPlayerSumOfHand(player: Player) {
            println(" - 결과: ${player.sumOfHand()}")
        }

        fun printPlayerBust(player: Player) {
            print("${player.name}는 파산했습니다!")
        }

        fun announceDealerDrawOneMoreCard() {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            println()
        }

        fun announceProfits(profits: List<ParticipantProfit>) {
            println("## 최종 수익")
            profits.forEach {
                print("${it.participant.name}: ")
                println(it.profit.toInt())
            }
        }
    }
}
