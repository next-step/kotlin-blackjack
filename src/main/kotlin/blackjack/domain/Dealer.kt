package blackjack.domain

import blackjack.domain.interfaces.CardFactory
import blackjack.domain.interfaces.RandomCardFactory

class Dealer() {
    fun give(cardFactory: CardFactory): Card {
        return cardFactory.create()
    }

    fun shareCards(): List<Card> {
        return BASIC_CARD_RANGE.map { give(RandomCardFactory()) }
    }

    fun ask(player: Player, input: String): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return player.needCard(input)
    }

    companion object {
        private val BASIC_CARD_RANGE = 1..2
    }
}
