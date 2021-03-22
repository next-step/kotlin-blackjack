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

    fun giveCard2(player: Player, hasAccepted: Boolean): PlayerDto {
        if (hasAccepted) {
            player.takeCard(cardPack.pickCard())
        }
        return player.toPlayerDto()
    }


    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
