package blackjack.view

import blackjack.model.Participants
import blackjack.model.playable.PlayableReaction
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player
import blackjack.model.result.DealerResult
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

    fun presentResult(participants: Participants) {
        presentDealerResult(participants.dealer.dealerResult(participants.players))
        participants.players.values.forEach { presentPlayerResult(it, participants.dealer) }
    }

    private fun presentPlayerResult(player: Player, dealer: Dealer) {
        println("${player.name} : ${player.result(dealer)}")
    }

    private fun presentDealerResult(dealerResult: DealerResult) {
        println("## 최종 승패")
        println("딜러 ${dealerResult.winningCount}승 ${dealerResult.drawingCount}무 ${dealerResult.drawingCount}패")
    }

    fun presentDealerAction(playableReaction: PlayableReaction) {
        when (playableReaction) {
            PlayableReaction.HIT -> println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            PlayableReaction.STAND -> println("딜러는 17이상이라 카드를 받지 않았습니다.")
        }
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
