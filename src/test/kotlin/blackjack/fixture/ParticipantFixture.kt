package blackjack.fixture

import blackjack.domain.Bet
import blackjack.domain.Dealer
import blackjack.domain.GameState
import blackjack.domain.Hands
import blackjack.domain.Player

internal fun dealerFixture(hands: Hands = Hands()): Dealer =
    Dealer(
        gameState =
            GameState(
                hands = hands,
            ),
    )

internal fun playerFixture(
    hands: Hands = Hands(),
    bet: Bet = Bet.zero(),
): Player =
    Player(
        name = "player",
        gameState =
            GameState(
                hands = hands,
                bet = bet,
            ),
    )
