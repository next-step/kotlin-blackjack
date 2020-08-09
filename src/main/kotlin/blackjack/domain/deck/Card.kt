package blackjack.domain.deck

data class Card(val pip: Pip, val suit: Suit) {

    val score = Pip.scoreOf(pip)
}
