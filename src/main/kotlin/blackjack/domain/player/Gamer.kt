package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Gamer(profile: Profile, cards: Cards = Cards.EMPTY) : BlackJackPlayer(profile, cards) {

    override fun receiveCard(card: Card): Player {
        return Gamer(profile, cards.addCards(card))
    }

    override fun turnOff(): Player {
        return Gamer(profile.turnOff(), cards)
    }

    override fun turnOn(): Player {
        return Gamer(profile.turnOn(), cards)
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
        private const val CAN_ACHIEVE_POINT = 21
    }
}
