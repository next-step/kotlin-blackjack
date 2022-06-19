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
        if (cardDeck.isLeft()) {
            player.receiveCard(cardDeck.getOne())
        } else {
            throw IllegalStateException("카드가 존재하지 않습니다.")
        }
    }

    fun hitOrStand(player: Player) {
        while (true) {
            if (!player.canHit()) {
                println("${player.name}의 카드가 21 이상입니다. 카드를 더 받을 수 없습니다. \n")
                break
            }
            val hit = InputView.hitOrStand(player.name)
            if (hit) {
                hit(player)
            }
            OutputView.cardOfPlayer(player)
            if (!hit) break
        }
    }
}
