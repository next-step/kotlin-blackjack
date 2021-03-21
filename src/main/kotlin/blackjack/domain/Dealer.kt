package blackjack.domain

import blackjack.ui.model.PlayerDto

class Dealer(
    private val players: Players,
    private val cardPack: CardPack
) {
    fun giveTwoCardsToAllPlayers(): List<PlayerDto> {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.giveToAllPlayers(cardPack)
        }
        return players.toPlayerDtos()
    }

    fun giveCard(player: Player) {
        player.takeCard(cardPack.pickCard())
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
