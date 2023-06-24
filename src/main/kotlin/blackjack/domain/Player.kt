package blackjack.domain

class Player(val name: String) {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun addCards(drawCards: List<Card>) {
        drawCards.forEach {
            cards.add(it)
        }
    }

    fun getCards(): String {
        var cardText = ""
        cards.forEachIndexed { index, card ->
            cardText += card.number.toString() + card.shape.value
            if (index != cards.lastIndex) cardText += ", "
        }
        return cardText
    }

    companion object {
        fun generatePlayers(nameList: List<String>): List<Player> {
            val playerList = mutableListOf<Player>()
            nameList.forEach {
                playerList.add(Player(it))
            }
            return playerList.toList()
        }
    }
}
