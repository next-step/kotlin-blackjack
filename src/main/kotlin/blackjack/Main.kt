package blackjack

import blackjack.domain.BlackJackCards
import blackjack.domain.BlackJackCardsMap
import blackjack.domain.BlackJackPlayer
import blackjack.domain.BlackJackPlayers
import blackjack.domain.BlackJackResult
import blackjack.view.BlackJackView

fun main() {
    val blackJackCardsMap = BlackJackCardsMap()
    val playerNames = BlackJackView.getPlayerName()
    val blackJackPlayers =
        BlackJackPlayers(
            playerNames.map {
                BlackJackPlayer(
                    it,
                    BlackJackCards(mutableListOf(blackJackCardsMap.get(), blackJackCardsMap.get())),
                )
            },
        )
    BlackJackView.drawBlackJackPlayersCards(blackJackPlayers)

    blackJackPlayers.players.forEach {
        while (it.isDrawPossible()) {
            if (!BlackJackView.getPlayerDrawCardYn(it)) {
                break
            }
            it.drawCard(blackJackCardsMap.get())
            BlackJackView.drawBlackJackPlayerCards(it)
        }
    }
    BlackJackView.drawBlackJackPlayersCardsWithResult(blackJackPlayers)
    BlackJackView.drawWinPlayer(BlackJackResult(blackJackPlayers))
}
