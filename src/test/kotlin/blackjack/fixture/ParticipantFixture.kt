package blackjack.fixture

import blackjack.domain.Dealer
import blackjack.domain.Hands
import blackjack.domain.Player
import blackjack.domain.PlayerStatus

internal fun dealerFixture(
    hands: Hands = Hands(),
    status: PlayerStatus = PlayerStatus.PLAYING,
): Dealer =
    Dealer(
        name = "dealer",
        hands = hands,
        status = status,
    )

internal fun playerFixture(
    hands: Hands = Hands(),
    status: PlayerStatus = PlayerStatus.PLAYING,
): Player =
    Player(
        name = "player",
        hands = hands,
        status = status,
    )
