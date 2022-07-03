package blackjack.view

import blackjack.domain.MatchResult
import blackjack.view.response.CardResponse

class GameResults(
    val dealerResult: GameResult,
    val playerResults: List<GameResult>
) {
}

class GameResult(
    val name: String,
    val hand: List<CardResponse>,
    val score: Int,
    val matchResults: List<MatchResult>
){
    fun printMatchResult(): String {
        val winCount = matchResults.count { it == MatchResult.WIN }
        val drawCount = matchResults.count { it == MatchResult.DRAW }
        val loseCount = matchResults.count { it == MatchResult.LOSE }

        val builder = StringBuilder()
        if(winCount != 0) builder.append("${winCount}승 ")
        if(drawCount != 0) builder.append("${drawCount}무 ")
        if(loseCount != 0) builder.append("${loseCount}패 ")
        return builder.toString()
    }
}

