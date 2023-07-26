package blackjack.domain

class Players(names: List<String>) {
    private val cards = mutableListOf<Card>()
    val players = names.map { Player(it, arrayListOf(randomCard(), randomCard())) }

    init {
        require(names.size == names.toSet().size)
    }

    fun getPlayer(name: String): Player {
        return players.find { it.name == name } ?: throw IllegalArgumentException("no such player")
    }

    fun getCard(player: Player) {
        player.addCard(randomCard())
    }

    private fun randomCard(): Card {
        val cardShape = CardShape.values().toList().shuffled()[0]
        val cardNumber = CardNumber.values().toList().shuffled()[0]
        val card = Card(cardShape, cardNumber)
        if (hasCard(card)) {
            return randomCard()
        }
        cards.add(card)
        return card
    }

    private fun hasCard(card: Card): Boolean {
        return cards.contains(card)
    }
}
