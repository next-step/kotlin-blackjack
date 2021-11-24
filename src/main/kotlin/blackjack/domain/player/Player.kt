package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.game.Rule
import blackjack.domain.game.Score

interface Player {
    fun receiveCard(card: Card): Player

    fun turnOff(): Player

    fun turnOn(): Player

    fun isBurst(): Boolean

    fun openCards(): Cards

    fun getPlayerName(): Name

    fun getHighestPoint(): Int

    fun canReceiveCard(): Boolean

    fun judgeResult(players: List<Player>, rule: Rule): Map<Player, List<Score>>
}
