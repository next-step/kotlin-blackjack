package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Bettings
import blackjack.domain.game.GameResult

abstract class BlackJackPlayer(
    val profile: Profile,
    val cards: Cards = Cards.EMPTY
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

    abstract override fun receiveCard(card: Card): Player

    abstract override fun turnOff(): Player

    abstract override fun turnOn(): Player

    abstract override fun canReceiveCard(): Boolean

    abstract override fun judge(bettings: Bettings, players: List<Player>): GameResult
}
