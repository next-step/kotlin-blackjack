package blackjack.domain.game

import blackjack.domain.player.Gamer

data class GamersResult(val playerResult: GamerResult, val dealerResult: GamerResult)

data class GamerResult(val result: GameResult.Type, val gamer: Gamer) {
    val name = gamer.name
}

data class DealerResult(val win: Int, val draw: Int, val lose: Int)

