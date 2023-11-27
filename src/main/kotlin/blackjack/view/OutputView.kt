package blackjack.view

import blackjack.model.player.Participants
import blackjack.model.card.Cards
import blackjack.model.player.PlayableReaction
import blackjack.model.result.PlayableResult
import blackjack.model.player.playable.impl.Dealer
import blackjack.model.player.playable.impl.Player
import blackjack.model.result.DealerResult

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
        println("## 최종 승패")
        presentDealerResult(participants.dealer.dealerResult(participants.players))
        presentPlayersResult(participants.players.results(participants.dealer))
    }

    private fun presentPlayersResult(playersResult: Map<Player, PlayableResult>) {
        playersResult
            .keys
            .forEach { player -> println("${player.name} : ${playersResult[player]}") }
    }

    private fun presentDealerResult(dealerResult: DealerResult) {
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
    return "${this.dealer.presentDealers()} - 결과: ${this.dealer.score().value}"
}

private fun Participants.presentPlayerWithScore(): String {
    return this.players.values.joinToString(separator = "\n") { "${it.presentPlayers()} - 결과: ${it.cards.totalScore().value}" }
}

private fun Participants.names(): String {
    return players.values.joinToString(separator = InputView.PARTICIPANTS_PRESENT_SEPARATOR) { it.name }
}

fun Cards.presentPlayers(): String {
    return cards.joinToString(separator = ", ") { "${it.cardRank.alias}${it.suit.alias}" }
}

fun Player.presentPlayers(): String {
    return "${this.name}카드 : ${this.cards.presentPlayers()}"
}

fun Dealer.presentDealers(): String {
    return "딜러 카드 : ${this.cards.presentPlayers()}"
}
