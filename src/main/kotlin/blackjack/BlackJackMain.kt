package blackjack

import blackjack.domain.Deck
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.vo.GameResultVO
import blackjack.vo.ParticipantScoreVO
import blackjack.vo.ParticipantVO
import blackjack.vo.PlayerGameResultVO

fun main() {
    val playerNames = InputView.readPlayerNames()

    val deck = Deck.shuffled()
    val participants = Participants.init(playerNames, deck)

    val participantVOs = participants.members().map { ParticipantVO.of(it.name, it.openedCards()) }
    ResultView.printCardHands(participantVOs)

    val gameResultVO = play(participants, deck)

    val participantScoreVOs = participants.members().map { ParticipantScoreVO(ParticipantVO.of(it), it.calculateScore().score) }
    ResultView.printParticipantScores(participantScoreVOs)
    ResultView.printGameResult(gameResultVO)
}

private fun play(participants: Participants, deck: Deck): GameResultVO {
    val players = participants.players
    players.forEach { player -> drawMore(deck, player) }

    val dealer = participants.dealer
    if (dealer.shouldHit()) {
        ResultView.printDealerHit()
        dealer.hit(deck.draw())
    }

    val playerGameResultVOs = players.map { PlayerGameResultVO(it.name, it.getGameResult(dealer)) }
    return GameResultVO.of(playerGameResultVOs)
}

private fun drawMore(deck: Deck, player: Player) {
    while (deck.isNotEmpty()) {
        if (player.burst) {
            return
        }

        if (InputView.readDrawMore(player.name)) {
            player.hit(deck.draw())
        } else {
            return
        }
        ResultView.printParticipant(ParticipantVO.of(player))
    }
}
