package blackjack.domain

import blackjack.common.PlayerDecision
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState

class DealerTurn(private val dealer: Dealer) {
    fun play(deck: CardDeck, printDealerSummary: () -> Unit): PlayerResult {
        val turn = BlackjackTurn(dealer)

        turn.makeDecision(dealer.shouldDrawCard) { deck.drawCard() }
        turn.makeDecision(false) { deck.drawCard() }

        printDealerSummary()

        return PlayerResult(dealer, turn.playerState)
    }
}

class PlayerTurn(private val player: Player) {
    fun play(deck: CardDeck, getPlayerDecision: () -> PlayerDecision, printPlayerSummary: () -> Unit): PlayerResult {
        val turn = BlackjackTurn(player)

        while (turn.playerState is PlayerState.Playing) {
            turn.makeDecision(getPlayerDecision() == PlayerDecision.HIT) { deck.drawCard() }
            printPlayerSummary()
        }

        return PlayerResult(player, turn.playerState)
    }
}

private class BlackjackTurn(player: Player) {
    var playerState: PlayerState = PlayerState.of(player)
        private set

    fun makeDecision(isHit: Boolean, drawCard: () -> Card) {
        val oldPlayerState = playerState

        if (oldPlayerState is PlayerState.Playing) {
            playerState = oldPlayerState.hitOrStand(isHit, drawCard)
        }
    }

    private fun PlayerState.Playing.hitOrStand(isHit: Boolean, drawCard: () -> Card) = if (isHit) hit(drawCard()) else stand()
}
