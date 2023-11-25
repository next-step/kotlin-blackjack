package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

interface State2 {

    fun draw(card: Card): State2

    fun stay(): State2

    fun getSum(): Int

    fun init(cards: List<Card>) {

    }

    fun receive(card: Card) {

    }

    fun cards(): Hand? {
        return null
    }
}
