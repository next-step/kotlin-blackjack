package blackjack.model

import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.playblestrategy.impl.DealerStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    private val participants: Participants,
) {
    fun start() {
        while (participants.isContinue()) {
            playingBlackJack(participants)
        }
    }

    private fun playingBlackJack(participants: Participants) {
        participants.players.values.forEach {
            it.playing(InputView.askHit(it), ShuffledPack)
        }
        val playing = participants.dealer.playing(DealerStrategy(participants.dealer.score()), ShuffledPack)
        OutputView.presentDealerAction(playing)
    }
}
