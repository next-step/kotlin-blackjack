package blackjack.domain

import javax.smartcardio.Card

class Player(private val name: Name, private val cards: List<Card> = listOf()) {
}