package blackjack.domain

import blackjack.ui.model.BlackJackResult
import blackjack.ui.model.PlayerDto

class Dealer(
    private val players: List<Player>,
    private val cardPack: CardPack
) {
    fun giveTwoCardsToAllPlayers(): List<PlayerDto> {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.forEach { it.takeCard(cardPack.pickCard()) }
        }
        return players.map { it.toPlayerDTO() }
    }

    fun giveCardUntilStop(
        askReceive: (String) -> Boolean,
        doAfterAnswer: (PlayerDto) -> Unit
    ): List<BlackJackResult> {
        players.forEach {
            askAndGiveCardToSinglePlayer(it, askReceive, doAfterAnswer)
        }
        return players.map { BlackJackResult(it) }
    }

    private fun askAndGiveCardToSinglePlayer(
        player: Player,
        askReceive: (String) -> Boolean,
        doAfterAnswer: (PlayerDto) -> Unit
    ) {
        while (askReceive(player.name)) {
            player.takeCard(cardPack.pickCard())
            doAfterAnswer(player.toPlayerDTO())
        }
        doAfterAnswer(player.toPlayerDTO())
    }

    companion object {
        private const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
