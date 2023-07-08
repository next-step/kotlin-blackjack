package domain.player

import domain.card.CardDeck

data class Players(val list: List<Player>) {

    fun hit(cardDeck: CardDeck, afterHit: ((Player) -> Unit)? = null) {
        list.forEach {
            it.hit(cardDeck.pop(), afterHit)
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
