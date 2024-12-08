package blackjack.fixture

import blackjack.domain.Dealer
import blackjack.domain.Hands
import blackjack.domain.ParticipantStatus
import blackjack.domain.Player

internal fun dealerFixture(
    hands: Hands = Hands(),
    status: ParticipantStatus = ParticipantStatus.PLAYING,
): Dealer =
    Dealer(
        name = "dealer",
        hands = hands,
        status = status,
    )

internal fun playerFixture(
    hands: Hands = Hands(),
    status: ParticipantStatus = ParticipantStatus.PLAYING,
): Player =
    Player(
        name = "player",
        hands = hands,
        status = status,
    )
