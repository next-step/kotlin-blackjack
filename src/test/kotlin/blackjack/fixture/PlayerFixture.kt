package blackjack.fixture

import blackjack.domain.Alive
import blackjack.domain.Cards
import blackjack.domain.ParticipantState
import blackjack.domain.Player
import java.math.BigDecimal

fun player(
    name: String = "pobi",
    betAmount: BigDecimal = BigDecimal.ZERO,
    cards: Cards,
    state: ParticipantState = Alive,
) = Player(
    name = name,
    betAmount = betAmount,
    cards = cards,
    state = state,
)
