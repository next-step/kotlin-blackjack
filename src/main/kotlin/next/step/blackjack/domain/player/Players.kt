package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards

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

    companion object {
        fun of(playerNames: PlayerNames, cardsGenerator: () -> List<Card>): Players {
            return Players(playerNames.map { Player.of(it, Cards.of(cardsGenerator())) }.toSet())
        }
    }
}
