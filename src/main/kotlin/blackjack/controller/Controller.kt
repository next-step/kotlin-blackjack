package blackjack.controller

import blackjack.application.Deck
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.RandomShuffleStrategy
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerFactory
import blackjack.domain.player.Players
import blackjack.dto.PlayerDto
import blackjack.dto.PlayersDto
import blackjack.view.ResultView

object Controller {
    private const val MINIMUM_NUMBER_OF_CARDS = 2

    fun start() {
        val cards = PlayingCards.shuffle(RandomShuffleStrategy())
        val deck = Deck(cards)
        val names = InputFilter.inputPlayer()
        val players = PlayerFactory.create(names, deck)

        init(players)
        checkBlackjack(players)
        play(players, deck)
        end(players)
    }

    private fun init(players: Players) {
        ResultView.printGameStartMessage(PlayersDto.from(players).getNames())
        PlayersDto.from(players).players.forEach {
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

    private fun play(players: Players, deck: Deck) {
        players.values.forEach { player ->
            doHitOrStay(player, deck)
        }
    }

    private fun doHitOrStay(player: Player, deck: Deck) {
        while (InputFilter.inputHitOrStay(PlayerDto.from(player).name)) {
            player.draw(deck.getCard())
            ResultView.printPlayerCards(PlayerDto.from(player))
        }
        if (player.cards.size() == MINIMUM_NUMBER_OF_CARDS) {
            ResultView.printPlayerCards(PlayerDto.from(player))
        }
    }

    private fun end(players: Players) {
        ResultView.printLineFeed()
        players.values.forEach {
            ResultView.printResultWithScore(PlayerDto.from(it))
        }
    }
}
