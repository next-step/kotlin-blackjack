package next.step.blackjack.domain.betting

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.dealer.Dealer

@JvmInline
value class BettingPlayers(private val players: Set<BettingPlayer>) : Set<BettingPlayer> by players {

    fun turn(chooseHit: (BettingPlayer) -> Boolean, cardGenerator: () -> Card, announce: (BettingPlayer) -> Unit) {
        players.forEach { player ->
            while (player.canHit() && chooseHit(player)) {
                player.hit(cardGenerator())
                announce(player)
            }
        }
    }

    fun fight(dealer: Dealer): BettingResults {
        return BettingResults.from(players.associate { it.name() to it.fight(dealer) })
    }

    fun cards(): List<List<Card>> = players.map { it.cards() }

    companion object {
        fun of(players: Set<BettingPlayer>) = BettingPlayers(players)
    }
}
