package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Dealer
import blackjack.model.player.Player

class BlackjackGame(
    val dealer: Dealer,
    val players: List<Player>,
    val cards: CardDeck,
) {
    fun initDraw(printPlayerInitStatus: (List<Player>) -> Unit) {
        players.forEach { player ->
            repeat(2) {
                player.draw(cards.pop())
            }
        }
        printPlayerInitStatus(players)
    }

    fun play(
        inputPlayerChoice: (String) -> Boolean,
        printPlayerCards: (Player) -> Unit
    ) {
        players.forEach { player ->
            while (!player.isFinished()) {
                hitOrStay(player, inputPlayerChoice)
                printPlayerCards(player)
            }
        }
    }

    fun result(printResult: (List<Player>) -> Unit) {
        printResult(players)
    }

    private fun hitOrStay(player: Player, inputPlayerChoice: (String) -> Boolean) {
        when (inputPlayerChoice(player.name)) {
            true -> player.draw(cards.pop())
            false -> player.stay()
        }
    }
}
