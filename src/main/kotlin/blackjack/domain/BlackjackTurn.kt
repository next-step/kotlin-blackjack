package blackjack.domain

import blackjack.Fetcher
import blackjack.Printer
import blackjack.common.PlayerDecision
import blackjack.common.PlayerSummary
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState

class DealerTurn(private val dealer: Dealer) {
    fun play(deck: CardDeck, printDealerSummary: Printer<PlayerSummary>): PlayerResult {
        val turn = BlackjackTurn(dealer)

        while (turn.playerState is PlayerState.Playing) {
            turn.makeDecision(dealer.shouldDrawCard) { deck.drawCard() }
        }

        printDealerSummary(PlayerSummary(dealer))

        return PlayerResult(dealer, turn.playerState)
    }
}

class PlayerTurn(private val player: Player) {
    fun play(deck: CardDeck, getPlayerDecision: Fetcher<String, PlayerDecision>, printPlayerSummary: Printer<PlayerSummary>): PlayerResult {
        val turn = BlackjackTurn(player)

        while (turn.playerState is PlayerState.Playing) {
            turn.makeDecision(getPlayerDecision(player.name) == PlayerDecision.HIT) { deck.drawCard() }
            printPlayerSummary(PlayerSummary(player))
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
