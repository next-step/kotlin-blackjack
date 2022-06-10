package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJackGame(
    private var cardDeck: CardDeck,
    private var _players: Players
) {

    val players: List<Player>
        get() = _players.players

    init {
        _players.players
            .map {
                it.receivedCards.add(cardDeck.pickCard())
                it.receivedCards.add(cardDeck.pickCard())
            }
    }

    fun playDealer(takeMoreDealerStrategy: TakeMoreDealerStrategy) {
        val dealer = _players.players
            .filterIsInstance<Dealer>()
            .first()

        while (dealer.canBeTakeOneCard(takeMoreDealerStrategy)) {
            dealer.addCard(cardDeck.pickCard())
        }
    }

    fun calculateWinner() {
        val dealer = _players.players
            .filterIsInstance<Dealer>()
            .first()

        val players = _players.players
            .filter { it !is Dealer }

        players.forEach {
            judge(it, dealer)
        }
    }

    private fun judge(player: Player, dealer: Dealer) {
        if (checkPlayerWin(player, dealer)) {
            player.isWinner = true
            dealer.lose++
        }

        if (checkDealerWin(player, dealer)) {
            player.isWinner = false
            dealer.win++
        }
    }

    private fun checkPlayerWin(player: Player, dealer: Dealer): Boolean {
        if (player.score > BLACKJACK_SCORE) {
            return false
        }

        return dealer.score > BLACKJACK_SCORE || player.score > dealer.score || player.score == BLACKJACK_SCORE
    }

    private fun checkDealerWin(player: Player, dealer: Dealer): Boolean {
        if (dealer.score > BLACKJACK_SCORE) {
            return false
        }

        return player.score > BLACKJACK_SCORE || player.score < dealer.score || dealer.score == BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
