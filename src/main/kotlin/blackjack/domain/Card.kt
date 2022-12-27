package blackjack.domain

data class Card(
    val cardNumber: CardNumber = CardNumber.values().random(),
    val pattern: Pattern = Pattern.values().random()
)
