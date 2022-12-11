package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.card.strategy.ShuffledDeckGenerateStrategy
import blackjack.domain.player.PlayerFactory

object Controller {
    fun start() {
        val deck = Deck(ShuffledDeckGenerateStrategy())
        val names = InputFilter.inputPlayer()
        val players = PlayerFactory.create(names, deck)
    }
}
