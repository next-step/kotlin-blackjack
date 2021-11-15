package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

interface Player {
    fun receiveCard(card: Card): Player

    fun turnOff(): Player

    fun turnOn(): Player

    fun isBurst(): Boolean

    fun openCards(): Cards

    fun getPlayerName(): Name

    fun getHighestPoint(): Int
}
