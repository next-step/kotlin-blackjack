package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Role

object GameManager {
    fun playPlayer(player: Role, deck: Deck, printParticipantCards: (Role) -> Unit): Role {
        val newPlayer = player.draw(deck.getCard())
        printParticipantCards(newPlayer)
        return newPlayer
    }

    fun canPlayerHit(player: Role, printPlayerBust: (Role) -> Unit, printPlayerBlackjack: (Role) -> Unit): Boolean {
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

    fun playDealer(dealer: Dealer, deck: Deck, printDealerDrawMessage: () -> Unit): Role {
        if (dealer.score <= Dealer.STOP_SCORE) {
            printDealerDrawMessage()
            return dealer.draw(deck.getCard())
        }
        return dealer.stay()
    }

    fun stay(role: Role): Role {
        if (!role.isBust && !role.isBlackjack) {
            return role.stay()
        }
        return role
    }
}
