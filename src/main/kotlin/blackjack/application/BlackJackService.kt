package blackjack.application

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.GameResult
import blackjack.view.GameResults
import blackjack.view.response.CardResponse

private const val FIRST_DRAW_COUNT = 2

object BlackJackService {

    fun createPlayers(names: List<String>): List<Player> {
        require(names.size in Player.MIN_COUNT..Player.MAX_COUNT) { "플레이어 수는 1 이상 25 이하여야 합니다." }

        return names.map { Player(it) }
    }

    fun calculate(dealer: Dealer, players: List<Player>): GameResults {
        val dealerResult = GameResult(
            dealer.name,
            dealer.hand.cards.map { card -> CardResponse.from(card) },
            dealer.score.score,
            players.map { it.getMatchResult(dealer).inverse() }
        )
        val playersResults = players.map {
            GameResult(
                it.name,
                it.hand.cards.map { card -> CardResponse.from(card) },
                it.score.score,
                listOf(it.getMatchResult(dealer))
            )
        }
        return GameResults(dealerResult, playersResults)
    }

    fun drawFirst(dealer: Dealer, players: List<Player>, deck: Deck) {
        draw(dealer, deck, FIRST_DRAW_COUNT)
        players.forEach { draw(it, deck, FIRST_DRAW_COUNT) }
    }

    fun draw(player: Player, deck: Deck, drawCount: Int) {
        repeat(drawCount) {
            player.addCard(deck.draw())
        }
    }
}
