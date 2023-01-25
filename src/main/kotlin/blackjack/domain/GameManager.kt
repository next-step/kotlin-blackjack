package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Role

class GameManager(
    private val askHit: (Role) -> Boolean,
    private val printParticipantCards: (Role) -> Unit,
    private val printPlayerBust: (Role) -> Unit,
    private val printPlayerBlackjack: (Role) -> Unit,
    private val printDealerDrawMessage: () -> Unit
) {
    fun play(players: Participants, deck: Deck): Participants {
        val newPlayers = players.getPlayers().map { player ->
            inputPlayersHitOrStay(player, deck)
        }
        val newDealer = playDealer(players.getDealer(), deck)
        return Participants(newPlayers) + newDealer
    }

    private fun playDealer(dealer: Dealer, deck: Deck): Role {
        if (dealer.score <= Dealer.STOP_SCORE) {
            printDealerDrawMessage()
            return dealer.draw(deck.getCard())
        }
        return dealer.stay()
    }

    private fun inputPlayersHitOrStay(player: Role, deck: Deck): Role {
        var newPlayer = player
        while (askHit(newPlayer) && canPlayerHit(newPlayer)) {
            newPlayer = playPlayer(newPlayer, deck)
        }
        if (newPlayer.hasOnlyTwoCards) {
            printParticipantCards(newPlayer)
        }
        return newPlayer.stay()
    }

    private fun playPlayer(player: Role, deck: Deck): Role {
        val newPlayer = player.draw(deck.getCard())
        printParticipantCards(newPlayer)
        return newPlayer
    }

    private fun canPlayerHit(player: Role): Boolean {
        if (player.isBust) {
            printPlayerBust(player)
            return false
        }
        if (player.isBlackjack) {
            printPlayerBlackjack(player)
            return false
        }
        return true
    }
}
