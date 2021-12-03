package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Credit

abstract class BlackJackPlayer(
    val profile: Profile,
    val cards: Cards = Cards.EMPTY,
    val credit: Credit = Credit.from(0)
) : Player {
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

    override fun hasBlackJack(): Boolean {
        return cards.isBlackJack()
    }

    override fun getPlayerCredit(): Credit {
        return credit
    }

    abstract override fun loseBetting(): Player

    abstract override fun loseBetting(player: Player): Player

    abstract override fun receiveCard(card: Card): Player

    abstract override fun turnOff(): Player

    abstract override fun turnOn(): Player

    abstract override fun canReceiveCard(): Boolean

    abstract override fun winBetting(player: Player): Player

    fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
        return map {
            if (block(it)) newValue else it
        }
    }
}

