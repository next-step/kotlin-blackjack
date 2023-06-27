package blackjack.fixture

import blackjack.domain.Cards
import blackjack.domain.Player
import blackjack.domain.State
import java.math.BigDecimal

fun player(
    name: String = "pobi",
    betAmount: BigDecimal = BigDecimal.ZERO,
    cards: Cards,
) = Player.of(
    name = name,
    betAmount = betAmount,
    cards = cards
)

fun player(
    name: String = "pobi",
    betAmount: BigDecimal = BigDecimal.ZERO,
    state: State,
) = Player(
    name = name,
    betAmount = betAmount,
    state = state,
)
