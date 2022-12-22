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
            result = result or it.cards.isBlackjack()
        }
        return result
    }

    fun play(players: Players, deck: Deck) {
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
}
