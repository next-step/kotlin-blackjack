package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Role

object GameManager {
    private lateinit var askHit: (Role) -> Boolean
    private lateinit var printParticipantCards: (Role) -> Unit
    private lateinit var printPlayerBust: (Role) -> Unit
    private lateinit var printPlayerBlackjack: (Role) -> Unit

    fun play(players: Participants, deck: Deck, askHit: (Role) -> Boolean, printParticipantCards: (Role) -> Unit, printPlayerBust: (Role) -> Unit, printPlayerBlackjack: (Role) -> Unit): Participants {
        this.askHit = askHit
        this.printParticipantCards = printParticipantCards
        this.printPlayerBust = printPlayerBust
        this.printPlayerBlackjack = printPlayerBlackjack

        val newPlayers = players.getPlayers().map { player ->
            inputPlayersHitOrStay(player, deck)
        }
        return Participants(newPlayers)
    }

    fun playDealer(dealer: Dealer, deck: Deck, printDealerDrawMessage: () -> Unit): Role {
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
