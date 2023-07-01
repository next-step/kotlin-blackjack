package blackjack.domain.gamer

import blackjack.domain.money.Money

fun player(
    name: String = "test",
    betAmount: Money = Money(1),
): Player {
    return Player(name, betAmount)
}
