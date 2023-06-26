package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers
import blackjack.domain.player.Players
import blackjack.domain.result.GameResultManager
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.model.GamePlayerConverter
import blackjack.ui.model.PlayerViewConverter
import blackjack.ui.model.PlayerViewModel

object Controller {
    fun start() {
        val deck = CardDeck()
        val players = getPlayers(deck)
        val playersModels = convertToViewModels(players)
        OutputView.printInitState(playersModels, CardDeck.INIT_DRAW_SIZE)

        doEachTurn(players.getGamePlayers(), deck)
        doDealerTurn(players.getDealer(), deck)
        val scoreResults = GameResultManager.getGameResults(players)
        OutputView.printResults(players.getDealer(), scoreResults)
        OutputView.printMatchResults(scoreResults)
    }

    private fun getPlayers(deck: CardDeck): Players {
        val gamePlayers = createPlayers(deck)
        val dealer = createDealer(deck)
        val players = Players(dealer, gamePlayers)
        return players
    }

    private fun doDealerTurn(dealer: Dealer, deck: CardDeck) {
        if (dealer.isEligibleToHit()) {
            OutputView.printDealerOneMoreDraw()
            dealer.draw(deck)
        }
    }

    private fun convertToViewModels(players: Players): List<PlayerViewModel> {
        return players.players.map(PlayerViewConverter::convert)
    }

    private fun createDealer(deck: CardDeck): Dealer {
        return Dealer(deck.draw(CardDeck.INIT_DRAW_SIZE))
    }

    private fun createPlayers(deck: CardDeck): GamePlayers {
        val playerNames = InputView.getPlayerName()
        return playerNames.map { GamePlayer(it, deck.draw(CardDeck.INIT_DRAW_SIZE)) }.let(::GamePlayers)
    }

    private fun doEachTurn(gamePlayers: GamePlayers, deck: CardDeck) {
        gamePlayers.players.forEach { player -> turn(deck, player) }
    }

    private fun turn(deck: CardDeck, player: GamePlayer) {
        while (player.isEligibleToHit() && InputView.isHit(player.name)) {
            player.draw(deck)
            val playerViewModel = GamePlayerConverter.convert(player)
            OutputView.printPlayersCard(playerViewModel)
        }
        printIfNotDrawPlayer(player)
    }

    private fun printIfNotDrawPlayer(player: GamePlayer) {
        if (player.cards.size == CardDeck.INIT_DRAW_SIZE) {
            val playerViewModel = GamePlayerConverter.convert(player)
            OutputView.printPlayersCard(playerViewModel)
        }
    }
}

fun main() {
    Controller.start()
}
