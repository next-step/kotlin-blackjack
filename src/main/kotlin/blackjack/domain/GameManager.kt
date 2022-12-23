package blackjack.domain

import blackjack.application.Deck
import blackjack.controller.InputFilter
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.dto.PlayerDto
import blackjack.view.ResultView

object GameManager {
    private const val MINIMUM_NUMBER_OF_CARDS = 2

    fun checkBlackjack(players: Players): Boolean {
        var result = false
        players.values.forEach {
            result = result or it.isBlackjack()
        }
        return result
    }

    fun play(players: Players, deck: Deck): Players {
        val newPlayers = players.values.map {
            doHitOrStay(it, deck)
        }
        return Players(newPlayers)
    }

    private fun doHitOrStay(player: Player, deck: Deck): Player {
        var newPlayer = player
        while (InputFilter.inputHitOrStay(PlayerDto.from(player).name)) {
            newPlayer = player.draw(deck.getCard())
            ResultView.printPlayerCards(PlayerDto.from(newPlayer))
        }
        if (!player.isBust() || !player.isBlackjack()) {
            newPlayer = player.stay()
        }
        if (player.getCardsSize() == MINIMUM_NUMBER_OF_CARDS) {
            ResultView.printPlayerCards(PlayerDto.from(newPlayer))
        }
        return newPlayer
    }
}
