package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player

object OutputView {
    private const val DELIMITER = "\n"
    private const val INITIAL_CARD_SIZE = 2

    fun printInitialState(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장을 나누어 주었습니다.")
        players.map { println(printPerson(it)) }
        println()
    }

    fun printCardState(player: Player) {
        println(printPerson(player))
        println()
    }

    fun printResult(players: List<Player>) {
        println(players.joinToString(DELIMITER) { "${printPerson(it)} - ${it.getScore()}" })
        println()
    }

    fun printGameResult(gameResult: GameResult) {
        val winParticipantsCnt = gameResult.participantResult.count { it.isWin() }
        val loseParticipantsCnt = gameResult.participantResult.count { !it.isWin() }
        println("## 최종 승패")
        println("${gameResult.dealerName}: ${loseParticipantsCnt}승 ${winParticipantsCnt}패")
        gameResult.participantResult.forEach { println("${it.name}: ${it.winOrLose.description}") }
    }

    fun printDealerPickOneMoreCard(dealer: Dealer) {
        if (dealer.cards.cards.size <= INITIAL_CARD_SIZE)
            return
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    private fun printPerson(player: Player): String {
        return "${player.name}카드: ${player.cards}"
    }
}
