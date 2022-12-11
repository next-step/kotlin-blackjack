package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.card.strategy.ShuffledDeckGenerateStrategy
import blackjack.domain.player.PlayerFactory
import blackjack.dto.PlayersDto
import blackjack.view.ResultView

object Controller {
    fun start() {
        val deck = Deck(ShuffledDeckGenerateStrategy())
        val names = InputFilter.inputPlayer()
        val players = PlayerFactory.create(names, deck)

        ResultView.printGameStartMessage(PlayersDto(players).getNames())
        PlayersDto(players).getPlayers().forEach {
            ResultView.printPlayerCards(it)
        }
    }
}
