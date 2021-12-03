package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Bet
import blackjack.domain.game.Credit

interface Player {
    fun receiveCard(card: Card): Player

    fun turnOff(): Player

    fun turnOn(): Player

    fun isBurst(): Boolean

    fun openCards(): Cards

    fun getPlayerName(): Name

    fun getHighestPoint(): Int

    fun canReceiveCard(): Boolean

    fun hasBlackJack(): Boolean

    fun holdBetting(getPlayerBetting: Bet): Player

    fun winBetting(): Player

    fun winBetting(player: Player): Player

    fun winBlackJack(): Player

    fun loseBlackJack(player: Player): Player

    fun loseBetting(): Player

    fun loseBetting(player: Player): Player

    fun getPlayerCredit(): Credit

    fun minusCredit(credit: Credit): Player

    fun receiveCredit(credit: Credit): Player
}
