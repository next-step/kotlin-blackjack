package blackjack.domain

data class Players(val list: List<Player>) {
    fun drawAllPlayer(cardDeck: CardDeck) {
        list.forEach { it.draw(cardDeck) }
    }
}
