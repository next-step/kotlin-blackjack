package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

data class Gamer(
    val name: Name,
    val playerStatus: PlayerStatus = PlayerStatus.STOP,
    val cards: Cards = Cards.EMPTY
) : Player {

    override fun receiveCard(card: Card): Gamer {
        return Gamer(name, playerStatus, cards.addCards(card))
    }

    override fun turnOff(): Gamer {
        return Gamer(name, PlayerStatus.STOP, cards)
    }

    override fun turnOn(): Gamer {
        return Gamer(name, PlayerStatus.BURST, cards)
    }

    override fun isBurst(): Boolean {
        return playerStatus == PlayerStatus.BURST
    }

    override fun openCards(): Cards {
        return cards
    }

    override fun getPlayerName(): Name {
        return name
    }

    override fun getHighestPoint(): Int {
        return cards.getHighestPoint()
    }
}
