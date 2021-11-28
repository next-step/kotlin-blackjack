package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Bettings
import blackjack.domain.game.GameResult

interface Player {
    fun receiveCard(card: Card): Player

    fun turnOff(): Player

    fun turnOn(): Player

    fun isBurst(): Boolean

    fun openCards(): Cards

    fun getPlayerName(): Name

    fun getHighestPoint(): Int

    fun canReceiveCard(): Boolean

    fun judge(bettings: Bettings, players: List<Player>): GameResult

    fun hasBlackJack(): Boolean
}
