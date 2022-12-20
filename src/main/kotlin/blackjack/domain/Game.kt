package blackjack.domain

import blackjack.InputView
import blackjack.ResultView
import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import blackjack.model.PlayerGameResults

class Game(val players: Players, val dealer: Dealer) {
    init {
        dealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        players.value.forEach {
            List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
                .let(it::readyToPlay)
        }
        List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
            .let(dealer::readyToPlay)
    }

    fun play() {
        playPlayers(players, dealer)
        playDealer(dealer)
        println()
    }

    fun results(): PlayerGameResults {
        val playerResults = calculatePlayerResult(players, dealer)
        val gameDealerGameResult = calculateDealerResult(playerResults)
        return PlayerGameResults(listOf(gameDealerGameResult) + playerResults)
    }

    private fun calculatePlayerResult(players: Players, dealer: Dealer): List<PlayerGameResult> {
        val playerResults = mutableListOf<PlayerGameResult>()
        players.value.map { player ->
            val result = calculateGameResult(player, dealer)
            playerResults.add(PlayerGameResult.Player(player.name, result))
        }
        return playerResults
    }

    private fun calculateDealerResult(playerResults: List<PlayerGameResult>): PlayerGameResult.Dealer =
        PlayerGameResult.Dealer(
            name = dealer.name,
            win = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.LOSE },
            push = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.PUSH },
            lose = playerResults.count { it is PlayerGameResult.Player && it.gameResult == GameResult.WIN },
        )

    private fun calculateGameResult(player: Player, dealer: Dealer): GameResult {
        val playerSum = player.sumCards()
        val dealerSum = dealer.sumCards()
        return when {
            player.blackjack() && dealer.blackjack() -> GameResult.PUSH
            dealer.blackjack() -> GameResult.LOSE
            player.blackjack() || (dealer.bust() && !player.bust()) -> GameResult.WIN
            player.bust() -> GameResult.LOSE
            playerSum == dealerSum -> GameResult.PUSH
            playerSum > dealerSum -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    private fun playPlayers(players: Players, dealer: Dealer) {
        players.value
            .forEach { player ->
                while (!player.blackjack() &&
                    !player.bust() &&
                    InputView.shouldHit(player)
                ) {
                    player.hit(dealer.deliverCard())
                    ResultView.printPlayerCards(player)
                }
            }
        println()
    }

    private fun playDealer(dealer: Dealer) {
        while (!dealer.blackjack() && !dealer.stay() && !dealer.bust()) {
            InputView.printDealerHit()
            dealer.hit(dealer.deliverCard())
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
