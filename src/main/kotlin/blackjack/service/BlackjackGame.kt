package blackjack.service

import blackjack.model.CardDeck
import blackjack.model.Player

private const val INIT_CARD_COUNT = 2
private const val CARD_PICK_STOP_SYMBOL = "n"

class BlackjackGame(
    private val cardDeck: CardDeck,
    val players: List<Player>
) {
    init {
        repeat(INIT_CARD_COUNT) {
            players.forEach { it.addCard(cardDeck.getCard()) }
        }
    }

    fun play(answerReader: (Player) -> String, cardsPrinter: (Player) -> Unit) {
        players.forEach { player ->
            pickCardsUntilStopAnswered(player, answerReader, cardsPrinter)
        }
    }

    private fun pickCardsUntilStopAnswered(
        player: Player,
        answerReader: (Player) -> String,
        cardsPrinter: (Player) -> Unit
    ) {
        while (player.isPickable()) {
            val answer = answerReader(player)
            if (answer == CARD_PICK_STOP_SYMBOL) {
                break
            }

            player.addCard(cardDeck.getCard())
            cardsPrinter(player)
        }
    }
}
