package blackjack.domain

import blackjack.domain.card.Card

class Player(val name: String) : BlackJackGamer {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator(GamerType.PLAYER)

    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
    }

    override fun getGamerType(): GamerType {
        return GamerType.PLAYER
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
