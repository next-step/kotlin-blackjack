package blackjack.domain

import blackjack.ui.model.PlayerDto

class Dealer(
    private val players: Players,
    private val cardPack: CardPack
) : Player("딜러") {
    fun giveTwoCardsToAllPlayers(): List<PlayerDto> {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.giveToAllPlayers(cardPack)
        }
        return players.toPlayerDtos()
    }

    fun giveCard(player: Player, hasAccepted: Boolean): PlayerDto {
        if (hasAccepted) {
            player.takeCard(cardPack.pickCard())
        }
        return player.toPlayerDto()
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
