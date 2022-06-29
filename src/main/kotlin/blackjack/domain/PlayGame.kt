package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class PlayGame {
    private val cardDeck = CardDeck()
    fun start(player: Player) {
        repeat(2) {
            hit(player)
        }
    }

    fun hit(player: Player) {
        runCatching {
            player.receiveCard(cardDeck.getOne())
        }.onFailure {
            throw IllegalStateException("카드가 존재하지 않습니다.")
        }
    }

    fun hitOrStandMultipleTimes(player: Player) {
        var result: Boolean
        do {
            result = hitOrStand(player)
        } while (result)
    }

    private fun hitOrStand(player: Player): Boolean {
        if (!player.canHit()) {
            println("${player.name}의 카드가 21 이상입니다. 카드를 더 받을 수 없습니다. \n")
            return false
        }
        val hit = InputView.hitOrStand(player.name)
        if (hit) {
            hit(player)
        }
        OutputView.cardOfPlayer(player)
        if (!hit) return false

        return true
    }
}
