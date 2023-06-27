package blackjack.domain

import blackjack.domain.card.Card

class Player(val name: String) {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator(DynamicProceedAceStrategy())

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
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
