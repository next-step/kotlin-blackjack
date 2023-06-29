package blackjack.domain.gamer

import blackjack.domain.game.Money

fun playerInitProperties(vararg names: String): List<PlayerInitProperty> {
    return names.map { PlayerInitProperty(it, Money(1)) }
}
