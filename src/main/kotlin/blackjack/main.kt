package blackjack

import blackjack.domain.Behavior
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.ui.InputView

fun main() {
    val deck = Deck.init()
    deck.shuffle()
    val players = InputView.readPlayerNames()
        .map { Player(it) }

    //2장씩 나눠주기
    players.forEach {
        it.hit(deck.draw())
        it.hit(deck.draw())
        println(it.cards)
    }

    //플레이어 별 hit, stay 여부
    players.forEach {
        while (true) {
            when (InputView.readBehavior(it.name)) {
                Behavior.HIT -> {
                    it.hit(deck.draw())
                    println(it.name)
                    println(it.cards)
                }
                Behavior.STAY -> break
            }
        }
    }

    //결과 출력
    players.forEach { player ->
        println("${player.name} ${player.cards} - 결과 ${player.point}")
    }
}
