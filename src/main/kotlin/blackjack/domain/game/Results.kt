package blackjack.domain.game

import blackjack.domain.participant.Player

@JvmInline
value class Results(val revenues: Map<Player, Int>) {
    fun getDealerResultAmount(): Int {
        return revenues.values.sumOf { -it }
    }
}
