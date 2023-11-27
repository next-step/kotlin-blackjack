package blackjack

import blackjack.model.card.pack.Pack
import blackjack.model.player.Participants
import blackjack.model.player.playable.impl.Player
import blackjack.model.player.playblestrategy.PlayingStrategy
import blackjack.model.player.playblestrategy.impl.ConsoleInputStrategy
import blackjack.model.player.playblestrategy.impl.DealerStrategy
import blackjack.view.OutputView

class BlackJackGame(
    private val participants: Participants,
    private val pack: Pack,
) {
    fun start() {
        while (participants.isContinue()) {
            playingBlackJackTurn(pack)
        }
    }

    private fun playingBlackJackTurn(pack: Pack) {
        playersTurn(pack)
        val dealerScore = participants.dealer.score()
        dealerTurn(DealerStrategy(dealerScore), pack)
    }

    private fun dealerTurn(playingStrategy: PlayingStrategy, pack: Pack) {
        val playing = participants.dealer.playing(playingStrategy, pack)
        OutputView.presentDealerAction(playing)
    }

    private fun playersTurn(pack: Pack) {
        participants.players.values.forEach { player: Player ->
            player.playing(ConsoleInputStrategy(player), pack)
            OutputView.presentPlayer(player)
        }
    }
}
