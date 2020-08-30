package blackjack.model

import blackjack.model.card.Deck
import blackjack.model.profit.Profits
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players

class BlackJackGame(private val players: Players, private val deck: Deck = Deck.defaultDeck()) {
    var turn = 0
        private set

    fun setUpDealer(dealer: Dealer = Dealer()): Dealer = dealer.takeDefaultCards { deck.provideCards() }

    fun setUpPlayers(): Players = players.takeDefaultCards { deck.provideCards() }

    tailrec fun playByPlayer(
        player: Player,
        isHit: (Player) -> Boolean,
        showCards: (Player) -> Unit
    ): Player {
        val currentPlayer: Player = player

        if (isHit(currentPlayer) && currentPlayer.canDraw()) {
            val playerDone: Player = currentPlayer.draw(deck.provideCard()).also(showCards)
            return playByPlayer(playerDone, isHit, showCards)
        }
        if (currentPlayer.hasTwoCards()) showCards(currentPlayer)
        turn++
        return currentPlayer.stay()
    }

    fun isTurnOver() = turn == players.size()

    tailrec fun playByDealer(dealer: Dealer, showMessageOfGettingCard: (Dealer) -> Unit): Dealer {
        if (!dealer.canDraw()) return dealer
        showMessageOfGettingCard(dealer)
        return playByDealer(dealer.draw(deck.provideCard()), showMessageOfGettingCard)
    }

    fun getProfits(dealer: Dealer, players: Players): Profits = dealer.provideProfits(players)

    companion object {
        const val BLACKJACK_SCORE = 21
        const val DEFAULT_CARD_COUNT = 2
    }
}
