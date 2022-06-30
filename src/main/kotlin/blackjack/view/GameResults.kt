package blackjack.view

import blackjack.view.response.CardResponse

class GameResults (
    val result: List<GameResult>
) {
}

class GameResult(
    val name: String,
    val hand: List<CardResponse>,
    val score: Int
)
