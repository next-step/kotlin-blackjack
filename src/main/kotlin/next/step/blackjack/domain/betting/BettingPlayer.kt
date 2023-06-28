package next.step.blackjack.domain.betting

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.game.GameOdds
import next.step.blackjack.domain.player.Player

class BettingPlayer(private val player: Player, private val amount: BettingAmount) {
    fun name(): String = player.name()

    fun hit(card: Card) = player.hit(card)

    fun canHit(): Boolean = player.canHit()

    fun cardDescs(): Set<String> = player.cardDescs()

    fun cards(): List<Card> = player.cards()

    fun point(): Int = player.point()

    fun fight(dealer: Dealer): Int = amount * GameOdds.from(player.fight(dealer), player.isBlackjack())

    companion object {
        fun of(player: Player, amount: BettingAmount) = BettingPlayer(player, amount)
    }
}
