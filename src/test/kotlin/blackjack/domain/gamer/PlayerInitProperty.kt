package blackjack.domain.gamer

import blackjack.domain.money.Money

fun playerInitProperties(vararg names: String): List<PlayerInitProperty> {
    return names.map { PlayerInitProperty(it, Money(1)) }
}
