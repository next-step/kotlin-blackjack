package blackjack.ui

import blackjack.domain.card.Cards
import blackjack.domain.player.Dealer
import blackjack.domain.result.GameResult
import blackjack.domain.result.match.MatchState
import blackjack.ui.model.PlayerViewModel

object OutputView {
    private const val NAME_SEPARATOR = ", "
    private const val DEALER_NAME = "딜러"
    private const val WIN = "승"
    private const val LOSE = "패"
    private const val PUSH = "무"
    private const val DEFAULT_CNT_NUMBER = 0

    fun printInitState(players: List<PlayerViewModel>, initDrawSize: Int) {
        val playerNames = players.map { it.name }
        println("${playerNames.joinToString(NAME_SEPARATOR)} 에게 ${initDrawSize}장의 나누었습니다.")
        players.forEach(::printPlayersCard)
    }

    fun printPlayersCard(player: PlayerViewModel) {
        println("${player.name}카드: ${getCardsName(player.cards)}")
    }

    private fun getCardsName(cards: Cards): String =
        cards.cards.joinToString(NAME_SEPARATOR) { "${OutViewConstMap[it.cardNumber]}${OutViewConstMap[it.cardSymbol]}" }

    fun printResults(dealer: Dealer, scoreResults: List<GameResult>) {
        println("${DEALER_NAME}카드: ${getCardsName(dealer.cards)} - 결과: ${dealer.score.value}")
        scoreResults.forEach { (player, score) ->
            println("${player.name}카드: ${getCardsName(player.cards)} - 결과: ${player.score.value}")
        }
    }

    fun printDealerOneMoreDraw() {
        println("${DEALER_NAME}는 ${Dealer.ELIGIBLE_HIT_MAX_SCORE.value} 이하라 한장의 카드를 더 받았습니다.")
    }

    fun printMatchResults(scoreResults: List<GameResult>) {
        println("### 최종 승패 ###")
        printDealerMatchResult(scoreResults)
        scoreResults.forEach(::printPlayerMatchResult)
    }

    private fun printDealerMatchResult(scoreResults: List<GameResult>) {
        val scoreResultCountMap = scoreResults.groupingBy { it.matchState }.eachCount()
        println(
            "$DEALER_NAME: " +
                "${scoreResultCountMap[MatchState.WIN] ?: DEFAULT_CNT_NUMBER} $WIN," +
                "${scoreResultCountMap[MatchState.LOSE] ?: DEFAULT_CNT_NUMBER} $LOSE," +
                "${scoreResultCountMap[MatchState.PUSH] ?: DEFAULT_CNT_NUMBER} $PUSH"
        )
    }

    private fun printPlayerMatchResult(gameResult: GameResult) {
        println("${gameResult.player.name}: ${gameResult.matchState}")
    }
}
