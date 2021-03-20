package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.DealMachine
import blackjack.domain.Player
import blackjack.domain.PlayerName
import blackjack.domain.Players

object ResultView {
    private const val SHOW_CARDS_SUFFIX = "카드: "

    fun showScoreResult(players: Players) {
        println()
        players.players.forEach {
            showPlayerCardsAndScore(it)
        }
    }

    fun showInitialPlayerCards(players: Players) {
        println("${players.players.map { it.name }.joinToString(", ")}에게 ${DealMachine.INITIAL_DEAL_COUNT}장을 나누었습니다.")
        players.players.forEach {
            showPlayerCards(it)
        }
    }

    private fun showPlayerCardsAndScore(player: Player) {
        showPlayerName(player.name)
        showCardsAndScore(player.cards)
    }

    fun showPlayerCards(player: Player) {
        showPlayerName(player.name)
        showCards(player.cards)
    }

    private fun showPlayerName(playerName: PlayerName) {
        print("$playerName")
    }

    private fun showCards(cards: Cards) {
        println("$SHOW_CARDS_SUFFIX${cards.cards.joinToString(", ")}")
    }

    private fun showCardsAndScore(cards: Cards) {
        println("$SHOW_CARDS_SUFFIX${cards.cards.joinToString(", ")} - 결과: ${cards.calculate()}")
    }
}