package blackjack.domain.bet

import blackjack.domain.player.name.Name

@JvmInline
value class Bets(private val bets: Map<Name, Money>) {

    fun betMoney(name: Name): Money = bets.getValue(name)

    companion object {
        fun of(bets: Map<Name, Money>): Bets = Bets(bets.toMap())
    }
}
