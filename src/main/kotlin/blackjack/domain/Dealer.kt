package blackjack.domain

import blackjack.ui.model.BlackJackResult
import blackjack.ui.model.PlayerDto

class Dealer(
    val players: List<Player>,
    private val cardPack: CardPack
) {
    fun giveTwoCardsToAllPlayers(): List<PlayerDto> {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.forEach { it.takeCard(cardPack.pickCard()) }
        }
        return players.map { it.toPlayerDTO() }
    }

    fun giveCard(player: Player) {
        player.takeCard(cardPack.pickCard())
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
