package blackjack.fixture

import blackjack.domain.Dealer
import blackjack.domain.GameStatus
import blackjack.domain.Hands
import blackjack.domain.Player

internal fun dealerFixture(
    hands: Hands = Hands(),
    status: GameStatus = GameStatus.PLAYING,
): Dealer =
    Dealer(
        name = "dealer",
        hands = hands,
        status = status,
    )

internal fun playerFixture(
    hands: Hands = Hands(),
    status: GameStatus = GameStatus.PLAYING,
): Player =
    Player(
        name = "player",
        hands = hands,
        status = status,
    )
