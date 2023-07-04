package blackjack.domain.users

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.model.UserCards

interface User {
    val userCards: UserCards

    fun name(): String {
        return userCards.name
    }

    fun cards(): Cards {
        return userCards.cards
    }

    fun cardList(): List<Card> {
        return userCards.cards.cards
    }

    fun cardSize(): Int {
        return userCards.cards.size
    }

    fun addCard(card: Card) {
        userCards.cards = userCards.cards.addCard(card)
    }

    fun cardValues(): Int {
        return userCards.cards.value()
    }
}
