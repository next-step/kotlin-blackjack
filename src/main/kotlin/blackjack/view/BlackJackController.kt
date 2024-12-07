package blackjack.view

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.domain.MutableCards
import blackjack.domain.PlayerName
import blackjack.domain.Players

class BlackJackController {
    fun initPlayers(): Players {
        val players =
            Players(
                players =
                    inputPlayerNameView().map {
                        Player(PlayerName(it), MutableCards(cards = mutableListOf()))
                    },
            )
        return players
    }

    fun initBlackJack(
        players: Players,
        cardSet: CardDeck,
    ): BlackJack {
        val blackJack = BlackJack(players, cardSet)
        initBlackJackView(blackJack)
        return blackJack
    }

    fun addCard(blackJack: BlackJack) {
        addCardView(blackJack)
    }

    fun result(blackJack: BlackJack) {
        resultView(blackJack)
    }
}
