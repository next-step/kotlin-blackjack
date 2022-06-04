package blackjack.domain

data class Players(val list: List<Player>) {
    fun drawAllPlayer(cardDeck: CardDeck) {
        list.forEach { it.draw(cardDeck) }
    }

    fun forEach(action: (Player) -> Unit) = list.forEach(action)
}
