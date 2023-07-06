package blackjack.domain

class Player(
    val name: String,
    var bettingMoney: Int = 0,
) : Participant(name)
