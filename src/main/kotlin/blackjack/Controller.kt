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
import blackjack.ui.model.PlayerOutputModel

object Controller {
    fun start() {
        val deck = CardDeck()
        val gamePlayers = createPlayers(deck)
        val dealer = createDealer(deck)
        val playersModels = convertToModels(dealer, gamePlayers)
        OutputView.printInitState(playersModels, CardDeck.INIT_DRAW_SIZE)

        gamePlayers.players.forEach { player -> turn(deck, player) }
        val scoreResults = GameResultManager.getGameResults(gamePlayers, dealer)
        OutputView.printResults(scoreResults)
    }

    private fun convertToModels(
        dealer: Dealer,
        players: GamePlayers
    ): List<PlayerOutputModel> {
        val dealerOutputModel = DealerConverter.convert(dealer)
        val playerOutputModels = GamePlayerConverter.convert(players)
        return listOf(listOf(dealerOutputModel), playerOutputModels).flatten()
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
            val playerOutputModel = GamePlayerConverter.convert(player)
            OutputView.printPlayersCard(playerOutputModel)
        }

        val playerOutputModel = GamePlayerConverter.convert(player)
        if (playerOutputModel.cards.size == CardDeck.INIT_DRAW_SIZE) {
            OutputView.printPlayersCard(playerOutputModel)
        }
    }
}

fun main() {
    Controller.start()
}
