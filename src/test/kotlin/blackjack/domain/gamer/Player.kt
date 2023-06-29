package blackjack.domain.gamer

import blackjack.domain.game.Money

fun player(
    name: String = "test",
    betAmount: Money = Money(1),
): Player {
    return Player(name, betAmount)
}
