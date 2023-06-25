package blackjack

import blackjack.domain.GameResult.GameResultManager
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.model.PlayerConverter

object Controller {
    fun start() {
        val deck = CardDeck()
        val playerNames = InputView.getPlayerName()
        val players = playerNames.map { Player(it, deck.draw(CardDeck.INIT_DRAW_SIZE)) }.let(::Players)
        val playerOutputModels = PlayerConverter.convert(players)
        OutputView.printInitState(playerOutputModels, CardDeck.INIT_DRAW_SIZE)

        players.players.forEach { player -> turn(deck, player) }
        val scoreResults = GameResultManager.getGameResults(players)
        OutputView.printResults(scoreResults)
    }

    private fun turn(deck: CardDeck, player: Player) {
        while (player.isEligibleToHit() && InputView.isHit(player.name)) {
            player.draw(deck)
            val playerOutputModel = PlayerConverter.convert(player)
            OutputView.printPlayersCard(playerOutputModel)
        }

        val playerOutputModel = PlayerConverter.convert(player)
        if (playerOutputModel.cards.size == CardDeck.INIT_DRAW_SIZE) {
            OutputView.printPlayersCard(playerOutputModel)
        }
    }
}

fun main() {
    Controller.start()
}
