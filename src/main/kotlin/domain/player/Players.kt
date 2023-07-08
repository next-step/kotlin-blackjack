package domain.player

import domain.card.CardDeck

data class Players(val list: List<Player>) {

    fun hit(cardDeck: CardDeck) {
        list.forEach {
            it.hit(cardDeck.pop())
        }
    }

    fun playersCanReceiveMoreCard(): Players {
        return Players(
            list.filter {
                it.canReceiveMoreCard()
            }
        )
    }

    fun noMorePlayer(): Boolean {
        return list.isEmpty()
    }
}
