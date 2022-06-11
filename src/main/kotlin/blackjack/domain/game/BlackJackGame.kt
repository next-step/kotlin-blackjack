package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJackGame(
    cardDeck: CardDeck,
    private val _players: Players,
    private val _dealer: Dealer
) {

    val players: List<Player>
        get() = _players.players

    val dealer: Dealer
        get() = _dealer

    init {
        firstDeal(cardDeck)
    }

    private fun firstDeal(cardDeck: CardDeck) {
        (players + dealer).map {
            it.addCard(cardDeck.pickCard())
            it.addCard(cardDeck.pickCard())
        }
    }

    fun calculateWinner() {
        _players.players
            .forEach {
                judge(it, _dealer)
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
