package blackjack.view

import blackjack.model.ParticipantResults
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.Console.presentDealers
import blackjack.view.Console.presentPlayers

object OutputView {

    fun presentCards(participants: Participants) {
        println(participants.presentDealer())
        println(participants.presentPlayers())
    }

    fun presentScores(participants: Participants) {
        presentCardsWitScore(participants)
    }

    private fun presentCardsWitScore(participants: Participants) {
        print(System.lineSeparator())
        println(participants.presentDealerWithScore())
        println(participants.presentPlayerWithScore())
    }

    fun dealing(participants: Participants) {
        println("딜러와 ${participants.names()} 에게 2 장씩 나누었습니다.")
    }

    fun playerCardPresent(it: Player) {
        println(it.presentPlayers())
    }

    fun presentResult(blackJackResult: ParticipantResults) {
        println("## 최종 승패")
        println("딜러 ${blackJackResult.dealerWinCount()}승 ${blackJackResult.dealerLoseCount()}패")
        blackJackResult.playerResults
            .playerResult()
            .forEach { println("${it.first}: ${it.second}") }
    }
}

private fun Participants.presentPlayers(): String {
    return this.players.values.joinToString(separator = "\n") { it.presentPlayers() }
}

private fun Participants.presentDealer(): String {
    return this.dealer.presentDealers()
}

private fun Participants.presentDealerWithScore(): String {
    return "${this.dealer.presentDealers()} - 결과: ${this.dealer.score()}"
}

private fun Participants.presentPlayerWithScore(): String {
    return this.players.values.joinToString(separator = "\n") { "${it.presentPlayers()} - 결과: ${it.cards.totalScore()}" }
}

private fun Participants.names(): String {
    return players.values.joinToString(separator = InputView.PARTICIPANTS_PRESENT_SEPARATOR) { it.name }
}
