package blackjack.domain

class Player(
    val name: String,
    val money: Int = 0,
) : Participant()
