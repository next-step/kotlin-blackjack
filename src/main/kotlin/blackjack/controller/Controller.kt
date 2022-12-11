package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.card.strategy.ShuffledDeckGenerateStrategy
import blackjack.domain.player.PlayerFactory
import blackjack.domain.player.Players
import blackjack.dto.PlayerDto
import blackjack.dto.PlayersDto
import blackjack.view.ResultView

object Controller {
    fun start() {
        val deck = Deck(ShuffledDeckGenerateStrategy())
        val names = InputFilter.inputPlayer()
        val players = PlayerFactory.create(names, deck)

        init(players)
        checkBlackjack(players)
    }

    private fun init(players: Players) {
        ResultView.printGameStartMessage(PlayersDto(players).getNames())
        PlayersDto(players).getPlayers().forEach {
            ResultView.printPlayerCards(it)
        }
        ResultView.printLineFeed()
    }

    private fun checkBlackjack(players: Players) {
        if (hasBlackjack(players)) {
            end(players)
        }
    }

    private fun hasBlackjack(players: Players): Boolean {
        var result = false
        players.values.forEach {
            result = result or it.cards.isBlackjack()
        }
        return result
    }

    private fun end(players: Players) {
        ResultView.printLineFeed()
        players.values.forEach {
            ResultView.printResultWithScore(PlayerDto(it))
        }
    }
}
