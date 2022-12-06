package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType

object BlackJack {

    fun firstPick(players: List<Player>): List<Player> {
        players.forEach { player ->
            repeat(2) {
                player.cards.add(pick())
            }
        }
        return players
    }

    fun pick(): Card {
        val number = CardNumber.getRandomNumber()
        val type = CardType.getRandomCardType()
        return Card(number, type)
    }
}
