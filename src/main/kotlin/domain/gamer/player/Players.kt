package domain.gamer.player

import domain.card.CardDeck

data class Players(val list: List<Player>) {

    fun hit(cardDeck: CardDeck) {
        list.forEach {
            it.hit(cardDeck)
        }
    }
}
