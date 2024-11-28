package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player

object ResultView {
    fun printFirstPhase(participants: List<Participant>) {
        printDrawTwoCardsEach(participants)
        participants.forEach { printParticipantsCards(it) }
        println()
    }

    private fun printDrawTwoCardsEach(participants: List<Participant>) {
        participants.joinToString { it.name }.also {
            println("${it}에게 2장의 카드를 나누었습니다.")
        }
    }

    fun printParticipantsCards(participant: Participant) {
        println("${participant.name} 카드: ${getCardsToStringInfo(participant)}")
    }

    private fun getCardsToStringInfo(player: Participant): String {
        val cards = player.cards
        return cards.joinToString(", ") { "${it.rank.score} ${it.suit}" }
    }

    fun printFinalResult(dealer: Dealer, players: List<Player>) {
        val participants = listOf(dealer) + players
        participants.forEach { printScoreResult(it) }
        printWinnerInfos(dealer, players)
    }

    private fun printScoreResult(participant: Participant) {
        val score = participant.cards.calculateScore()
        println("${participant.name} 카드 : ${getCardsToStringInfo(participant)} - 결과 : $score")
    }

    private fun printWinnerInfos(dealer: Dealer, players: List<Player>) {
        println("## 최종 승패")
        val playerToWinInfos = getPlayerToWinInfos(players, dealer)
        val looserCount = playerToWinInfos.count { !it.second }
        val winnerCount = playerToWinInfos.count { it.second }
        println("${dealer.name}: ${looserCount}승 ${winnerCount}패")
        printPlayerWinningInfos(playerToWinInfos)
    }

    private fun printPlayerWinningInfos(playerToWinInfos: List<Pair<Player, Boolean>>) {
        playerToWinInfos.forEach { (player, isWin) ->
            val result = if (isWin) "승리" else "패배"
            println("${player.name}: $result")
        }
    }

    private fun getPlayerToWinInfos(
        players: List<Player>,
        dealer: Dealer,
    ): List<Pair<Player, Boolean>> {
        return players.map { it to it.isWinner(dealer) }
    }

    fun printDealerDrawMessage(dealer: Dealer) {
        println("${dealer.name}는 16 이하라 한장의 카드를 더 받았습니다.")
    }
}
