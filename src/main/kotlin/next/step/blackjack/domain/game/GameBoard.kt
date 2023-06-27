package next.step.blackjack.domain.game

import next.step.blackjack.domain.betting.BettingAmount
import next.step.blackjack.domain.betting.BettingPlayer
import next.step.blackjack.domain.betting.BettingPlayers
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerNames
import next.step.blackjack.domain.player.Players

data class GameBoard(
    val gameCards: GameCards,
    val dealer: Dealer,
    val players: BettingPlayers
) {

    fun playersTurn(chooseHit: (BettingPlayer) -> Boolean, announce: (BettingPlayer) -> Unit) {
        players.turn(chooseHit, { popOneCard() }, announce)
    }

    private fun popOneCard(): Card = gameCards.pop()

    fun dealerTurn(announce: (String) -> Unit) {
        dealer.turn({ popOneCard() }, announce)
    }

    fun finish(announce: (Dealer, BettingPlayers) -> Unit) {
        announce(dealer, players)
    }

    companion object {
        private const val INIT_CARD_CNT = 2
        fun start(
            playerNames: PlayerNames,
            chooseBet: (Player) -> BettingAmount,
            announce: (Dealer, BettingPlayers, Int) -> Unit
        ): GameBoard {
            val gameCards = GameCards.shuffled()
            val dealer = Dealer.of(Cards.of(popInitCntCards(gameCards)))
            val players = Players.of(playerNames) { popInitCntCards(gameCards) }.bet(chooseBet)
            announce(dealer, players, INIT_CARD_CNT)
            return GameBoard(gameCards, dealer, players)
        }

        private fun popInitCntCards(gameCards: GameCards): List<Card> = gameCards.pop(INIT_CARD_CNT)
    }
}
