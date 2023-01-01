package blackjack.domain

import blackjack.application.Deck
import blackjack.domain.participant.state.role.Dealer
import blackjack.domain.participant.state.role.Role

object GameManager {
    fun hit(role: Role, deck: Deck): Role {
        return role.draw(deck.getCard())
    }

    fun canDealerHit(dealer: Dealer): Boolean {
        return dealer.getScore() <= Dealer.STOP_SCORE
    }

    fun stay(role: Role): Role {
        if (!role.isBust() && !role.isBlackjack()) {
            return role.stay()
        }
        return role
    }
}
