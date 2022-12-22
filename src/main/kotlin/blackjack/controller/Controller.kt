package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.GameManager
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.player.PlayerFactory
import blackjack.domain.player.Players
import blackjack.dto.PlayerDto
import blackjack.dto.PlayersDto
import blackjack.view.ResultView

object Controller {
    fun start() {
        val cards = PlayingCards.shuffle(RandomShuffleStrategy())
        val deck = Deck(cards)
        val names = InputFilter.inputPlayer()
        val players = PlayerFactory.create(names, deck)

        init(players)
        if (GameManager.checkBlackjack(players)) {
            end(players)
            return
        }
        GameManager.play(players, deck)
        end(players)
    }

    private fun init(players: Players) {
        ResultView.printGameStartMessage(PlayersDto.from(players).getNames())
        PlayersDto.from(players).players.forEach {
            ResultView.printPlayerCards(it)
        }
        ResultView.printLineFeed()
    }

    private fun end(players: Players) {
        ResultView.printLineFeed()
        players.values.forEach {
            ResultView.printResultWithScore(PlayerDto.from(it))
        }
    }
}
