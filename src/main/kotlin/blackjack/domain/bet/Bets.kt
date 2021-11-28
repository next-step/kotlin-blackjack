package blackjack.domain.bet

import blackjack.domain.player.name.Name

@JvmInline
value class Bets(private val bets: Map<Name, Money>) {

    companion object {
        fun of(bets: Map<Name, Money>): Bets = Bets(bets.toMap())
    }
}
