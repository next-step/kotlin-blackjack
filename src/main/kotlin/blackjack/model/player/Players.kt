package blackjack.model.player

import blackjack.model.card.CardDeck

class Players(val lists: List<Player>) {
    constructor(names: List<String>, inputBettingAmount: (String) -> Double) : this (
        lists = names.map { name ->
            Player(name, inputBettingAmount(name))
        }
    )

    fun draw(cards: CardDeck) {
        lists.forEach { player ->
            repeat(2) {
                player.draw(cards.pop())
            }
        }
    }
}
