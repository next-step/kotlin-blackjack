package blackjack.domain.card

const val ACE_SOFT = 10
const val BLACKJACK = 21

data class Card(val cardSuit: CardSuit, val cardNumber: CardNumber)
