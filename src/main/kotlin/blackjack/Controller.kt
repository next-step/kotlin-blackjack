package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers
import blackjack.domain.result.GameResultManager
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.model.DealerConverter
import blackjack.ui.model.GamePlayerConverter
import blackjack.ui.model.PlayerViewModel

object Controller {
    fun start() {
        val deck = CardDeck()
        val gamePlayers = createPlayers(deck)
        val dealer = createDealer(deck)
        val playersModels = convertToViewModels(dealer, gamePlayers)
        OutputView.printInitState(playersModels, CardDeck.INIT_DRAW_SIZE)

        doEachTurn(gamePlayers, deck)
        doDealerTurn(dealer, deck)
        val scoreResults = GameResultManager.getGameResults(gamePlayers, dealer)
        OutputView.printResults(dealer, scoreResults)
        OutputView.printMatchResults(scoreResults)
    }

    private fun doEachTurn(gamePlayers: GamePlayers, deck: CardDeck) {
        gamePlayers.players.forEach { player -> turn(deck, player) }
    }

    private fun doDealerTurn(dealer: Dealer, deck: CardDeck) {
        if (dealer.isEligibleToHit()) {
            OutputView.printDealerOneMoreDraw()
            dealer.draw(deck)
        }
    }

    private fun convertToViewModels(
        dealer: Dealer,
        players: GamePlayers
    ): List<PlayerViewModel> {
        val dealerViewModel = DealerConverter.convert(dealer)
        val playerViewModels = GamePlayerConverter.convert(players)
        return listOf(listOf(dealerViewModel), playerViewModels).flatten()
    }

    private fun createDealer(deck: CardDeck): Dealer {
        return Dealer(deck.draw(CardDeck.INIT_DRAW_SIZE))
    }

    private fun createPlayers(deck: CardDeck): GamePlayers {
        val playerNames = InputView.getPlayerName()
        return playerNames.map { GamePlayer(it, deck.draw(CardDeck.INIT_DRAW_SIZE)) }.let(::GamePlayers)
    }

    private fun turn(deck: CardDeck, player: GamePlayer) {
        while (player.isEligibleToHit() && InputView.isHit(player.name)) {
            player.draw(deck)
            val playerViewModel = GamePlayerConverter.convert(player)
            OutputView.printPlayersCard(playerViewModel)
        }

        val playerViewModel = GamePlayerConverter.convert(player)
        if (playerViewModel.cards.size == CardDeck.INIT_DRAW_SIZE) {
            OutputView.printPlayersCard(playerViewModel)
        }
    }
}

fun main() {
    Controller.start()
}
