package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card

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
        const val INIT_CARD_CNT = 2

        fun of(players: Set<Player>) = Players(players)

        fun of(playerNames: PlayerNames, cardsGenerator: (n: Int) -> List<Card>): Players {
            return Players(playerNames.map { Player.of(it, PlayerCards.of(cardsGenerator(INIT_CARD_CNT))) }.toSet())
        }
    }
}
