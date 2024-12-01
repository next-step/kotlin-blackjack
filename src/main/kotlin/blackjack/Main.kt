package blackjack

import blackjack.domain.BlackJackCards
import blackjack.domain.BlackJackPlayer
import blackjack.domain.BlackJackPlayerCards
import blackjack.domain.BlackJackPlayers
import blackjack.domain.BlackJackResult
import blackjack.view.BlackJackView

fun main() {
    val blackJackCards = BlackJackCards.getDefaultCards()
    val playerNames = BlackJackView.getPlayerName()
    val blackJackPlayers =
        BlackJackPlayers(
            playerNames.map {
                BlackJackPlayer(
                    it,
                    BlackJackPlayerCards(mutableListOf(blackJackCards.draw(), blackJackCards.draw())),
                )
            },
        )
    BlackJackView.drawBlackJackPlayersCards(blackJackPlayers)

    blackJackPlayers.players.forEach {
        while (it.isDrawPossible()) {
            if (!BlackJackView.getPlayerDrawCardYn(it)) break
            it.drawCard(blackJackCards.draw())
            BlackJackView.drawBlackJackPlayerCards(it)
        }
    }
    BlackJackView.drawBlackJackPlayersCardsWithResult(blackJackPlayers)
    BlackJackView.drawWinPlayer(BlackJackResult(blackJackPlayers))
}
