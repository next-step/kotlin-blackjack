package blackjack.ui

import blackjack.Dealer
import blackjack.Money
import blackjack.Participant
import blackjack.Player

class ConsoleOutput {
    companion object {
        fun announceAllParticipantsInitialized(
            dealer: Dealer,
            players: List<Player>,
        ) {
            println("${dealer.name}, ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        }

        fun revealAllParticipantsHeldCards(participants: List<Participant>) {
            participants.forEach { participant ->
                revealParticipantHeldCards(participant)
            }
            println()
        }

        fun revealParticipantHeldCards(participant: Participant) {
            print("${participant.name} 카드: ")
            print(participant.cards.toList().joinToString { "${it.denomination.face}${it.suit.koreanName}" })
            println()
        }

        fun announceBustParticipant(participant: Participant) {
            println("${participant.name}는 파산했습니다!")
        }

        fun announceBlackjackParticipant(participant: Participant) {
            println("${participant.name}는 블랙잭입니다!")
        }

        fun announceDealerDrawOneMoreCard(dealer: Dealer) {
            println()
            println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
            println()
        }

        fun revealAllParticipantsHeldCardsAndScores(participants: List<Participant>) {
            participants.forEach { participant ->
                print("${participant.name} 카드: ")
                print(participant.cards.toList().joinToString { "${it.denomination.face}${it.suit.koreanName}" })
                println(" - 결과: ${participant.cards.sum()}")
            }
            println()
        }

        fun announceAllParticipantsProfits(profits: List<Pair<Participant, Money>>) {
            println("## 최종 승패")
            profits.forEach { (participant, profit) ->
                println("${participant.name}: ${profit.toLong()}")
            }
        }
    }
}
