package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.player.Player

object StringParser {
    fun getPlayersFromString(input: String, deck: Deck): List<Player> {
        val inputs = input.split(",")
        return inputs.map { Player.of(it, deck) }.toList()
    }
}
