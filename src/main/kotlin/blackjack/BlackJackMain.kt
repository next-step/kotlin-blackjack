package blackjack

import blackjack.vo.ParticipantScoreVO
import blackjack.vo.ParticipantVO

fun main() {
    val playerNames = InputView.readPlayerNames()

    val deck = Deck.shuffled()
    val participants = Participants.init(playerNames, deck)

    val participantVOs = participants.members().map {
        ParticipantVO.of(it.name, it.openedCards())
    }
    ResultView.printCardHands(participantVOs)

    val players = participants.players
    players.forEach { player -> drawMore(deck, player) }

    val dealer = participants.dealer
    if (dealer.shouldHit()) {
        ResultView.printDealerHit()
        dealer.hit(deck.draw())
    }

    val participantScoreVOs = participants.members().map { ParticipantScoreVO(ParticipantVO.of(it), it.calculateScore()) }
    ResultView.printParticipantScores(participantScoreVOs)
}

private fun drawMore(deck: Deck, player: Player) {
    while (deck.isNotEmpty()) {
        if (InputView.readDrawMore(player.name)) {
            player.hit(deck.draw())
        } else {
            return
        }
        ResultView.printParticipant(ParticipantVO.of(player))
    }
}
