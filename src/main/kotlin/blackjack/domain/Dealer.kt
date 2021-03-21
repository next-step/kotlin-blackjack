package blackjack.domain

import blackjack.ui.model.BlackJackResult
import blackjack.ui.model.PlayerDTO

class Dealer(
    private val players: List<Player>,
    private val cardPack: CardPack
) {
    fun giveTwoCardsToAllPlayers(): List<PlayerDTO> {
        repeat(FIRST_GIVEN_CARD_SIZE) {
            players.forEach { it.takeCard(cardPack.pickCard()) }
        }
        return players.map { it.toPlayerDTO() }
    }

    fun giveCardUntilStop(
        askReceive: (String) -> Boolean,
        doAfterAnswer: (PlayerDTO) -> Unit
    ): List<BlackJackResult> {
        players.forEach {
            askAndGiveCardToSinglePlayer(it, askReceive, doAfterAnswer)
        }
        return players.map { BlackJackResult.from(it) }
    }

    private fun askAndGiveCardToSinglePlayer(
        player: Player,
        askReceive: (String) -> Boolean,
        doAfterAnswer: (PlayerDTO) -> Unit
    ) {
        while (askReceive(player.name)) {
            player.takeCard(cardPack.pickCard())
            doAfterAnswer(player.toPlayerDTO())
        }
        doAfterAnswer(player.toPlayerDTO())
    }

    companion object {
        const val FIRST_GIVEN_CARD_SIZE = 2
    }
}
