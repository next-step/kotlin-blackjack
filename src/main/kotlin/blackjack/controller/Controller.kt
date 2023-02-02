package blackjack.controller

import blackjack.domain.GameManager
import blackjack.domain.card.Deck
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.view.ResultView

object Controller {
    fun start() {
        val deck = Deck(RandomShuffleStrategy())
        val gameManager = GameManager(InputFilter, ResultView)
        gameManager.play(gameManager.prepare(deck), deck)
    }
}
