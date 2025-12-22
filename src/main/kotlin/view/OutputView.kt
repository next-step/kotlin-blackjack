package view

import domain.Dealer
import domain.Participant
import domain.Player
import domain.Players

class OutputView {
    companion object {
        fun printFirstRoundCard(
            players: Players,
            dealer: Dealer,
        ) {
            printFirstCard(players)
            printCardStatusOnFirstRound(dealer, players)
        }

        private fun printFirstCard(players: Players) {
            println("딜러와 ${players.players.joinToString(", ", transform = Player::name)}에게 2장의 카드를 나누었습니다.")
        }

        private fun printCardStatusOnFirstRound(
            dealer: Dealer,
            players: Players,
        ) {
            printCardStatusOnFirstRound(dealer)
            players.players.forEach { printCardStatusOnFirstRound(it) }
        }

        private fun printCardStatusOnFirstRound(participant: Participant) {
            println("${participant.name}카드: ${participant.getPublicCardsOnFirstRound()}")
        }

        fun printCardStatus(participant: Participant) {
            println("${participant.name}카드: ${participant.cards}")
        }

        fun printDealerMustGetCard() {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }

        fun printRoundResult(
            players: Players,
            dealer: Dealer,
        ) {
            players.players.forEach { printRoundResult(it) }
            printRoundResult(dealer)
        }

        private fun printRoundResult(participant: Participant) {
            println("${participant.name}카드: ${participant.cards} - 결과: ${participant.cards.calculateScore()}")
        }

        fun printFinalResult(
            dealer: Dealer,
            players: Players,
        ) {
            println("## 최종 수익")
            println("딜러: ${dealer.money}")
            players.players.forEach { player -> println("${player.name}: ${player.money}") }
        }
    }
}
