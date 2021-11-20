package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

data class Dealer(
    val profile: Profile,
    val cards: Cards = Cards.EMPTY
) : Player {

    override fun receiveCard(card: Card): Player {
        if (!canReceiveCard()) {
            return this
        }
        return Dealer(profile, cards.addCards(card))
    }

    override fun turnOff(): Player {
        return Dealer(profile.turnOff(), cards)
    }

    override fun turnOn(): Player {
        return Dealer(profile.turnOn(), cards)
    }

    override fun isBurst(): Boolean {
        return profile.isBurst()
    }

    override fun openCards(): Cards {
        return cards
    }

    override fun getPlayerName(): Name {
        return profile.name
    }

    override fun getHighestPoint(): Int {
        return cards.getHighestPoint()
    }

    override fun canReceiveCard(): Boolean {
        return getHighestPoint() <= CAN_ACHIEVE_POINT
    }

    companion object {
        private const val CAN_ACHIEVE_POINT = 16

        fun of(): Dealer {
            return Dealer(Profile.createDealer())
        }
    }
}
