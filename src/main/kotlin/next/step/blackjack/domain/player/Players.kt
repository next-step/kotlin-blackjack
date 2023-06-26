package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResults

@JvmInline
value class Players(private val players: Set<Player>) : Set<Player> by players {

    fun turn(chooseHit: (Player) -> Boolean, cardGenerator: () -> Card, announce: (Player) -> Unit) {
        players.forEach { player ->
            while (player.canHit() && chooseHit(player)) {
                player.hit(cardGenerator())
                announce(player)
            }
        }
    }

    fun fight(player: Player): GameResults {
        return GameResults.from(players.associate { it.name() to it.fight(player) })
    }

    companion object {
        fun of(players: Set<Player>) = Players(players)

        fun of(playerNames: PlayerNames, cardsGenerator: () -> List<Card>): Players {
            return Players(playerNames.map { Player.of(it, Cards.of(cardsGenerator())) }.toSet())
        }
    }
}
