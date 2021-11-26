package blackjack.ui

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.state.MatchResult
import blackjack.domain.player.state.hands.Hands
import blackjack.strategy.ui.output.OutputStrategy
import blackjack.util.FavoriteStringFixture.BLANK
import blackjack.util.FavoriteStringFixture.COMMA_AND_ONE_SPACE
import blackjack.util.FavoriteStringFixture.NEW_LINE

class ResultView(private val outputStrategy: OutputStrategy) {

    fun showReadiedPlayers(readiedDealer: Player, readiedGamePlayer: Players) {
        outputStrategy.execute(
            READIED_PLAYERS_INFORMATION.format(readiedDealer.name.name, playerNameJoinToString(readiedGamePlayer))
        )
        outputStrategy.execute(formatPlayerHandsInformation(readiedDealer))
        outputStrategy.execute(playerAndHandsJoinToString(readiedGamePlayer))
        outputStrategy.execute(BLANK)
    }

    private fun playerNameJoinToString(gamePlayers: Players): String =
        gamePlayers.players.joinToString(COMMA_AND_ONE_SPACE) { it.name.name }

    private fun playerAndHandsJoinToString(players: Players): String =
        players.players.joinToString(NEW_LINE) { formatPlayerHandsInformation(it) }

    fun showPlayerHands(player: Player) {
        outputStrategy.execute(
            HANDS_INFORMATION.format(player.name.name, handsJoinToString(player.playerState.hands))
        )
        outputStrategy.execute(BLANK)
    }

    private fun formatPlayerHandsInformation(player: Player) =
        HANDS_INFORMATION.format(player.name.name, handsJoinToString(player.playerState.hands))

    private fun handsJoinToString(hands: Hands): String =
        hands.hands.joinToString(COMMA_AND_ONE_SPACE) { cardJoinToString(it) }

    private fun cardJoinToString(card: Card): String =
        denominationName(card.denomination) + suitName(card.suit)

    fun noticeDealerDraw() {
        outputStrategy.execute(DEALER_DRAW_CARD)
        outputStrategy.execute(BLANK)
    }

    fun showEndedPlayers(endedDealer: Player, endedGamePlayer: Players) {
        outputStrategy.execute(formatHandsInformationAndScore(endedDealer))
        outputStrategy.execute(handsInformationAndScoreJoinToString(endedGamePlayer))
        outputStrategy.execute(BLANK)
    }

    private fun handsInformationAndScoreJoinToString(endedGamePlayer: Players): String =
        endedGamePlayer.players.joinToString(NEW_LINE) { formatHandsInformationAndScore(it) }

    private fun formatHandsInformationAndScore(player: Player): String =
        HANDS_INFORMATION_AND_SCORE.format(
            player.name.name,
            handsJoinToString(player.playerState.hands),
            player.playerState.hands.score().score
        )

    fun showMatchResult(endedDealer: Player, endedGamePlayer: Players) {
        outputStrategy.execute(PLAYERS_MATCH_RESULT_INTRODUCE)
        outputStrategy.execute(
            PLAYER_MATCH_RESULT.format(
                endedDealer.name.name,
                dealerMatchResultJoinToString(endedGamePlayer, endedDealer)
            )
        )
        outputStrategy.execute(gamePlayersMatchResultJoinToString(endedGamePlayer, endedDealer))
        outputStrategy.execute(BLANK)
    }

    private fun gamePlayersMatchResultJoinToString(endedGamePlayer: Players, endedDealer: Player) =
        endedGamePlayer.players
            .associateWith { it.match(endedDealer) }
            .toList()
            .joinToString(NEW_LINE) { PLAYER_MATCH_RESULT.format(it.first.name.name, matchResultName(it.second)) }

    private fun dealerMatchResultJoinToString(endedGamePlayer: Players, endedDealer: Player) =
        endedGamePlayer.players
            .map { endedDealer.match(it) }
            .groupingBy { it }
            .eachCount()
            .toList()
            .joinToString { MATCH_RESULT.format(it.second, matchResultName(it.first)) }

    private fun denominationName(denomination: Denomination): String {
        return when (denomination) {
            Denomination.ACE -> "A"
            Denomination.TWO -> "2"
            Denomination.THREE -> "3"
            Denomination.FOUR -> "4"
            Denomination.FIVE -> "5"
            Denomination.SIX -> "6"
            Denomination.SEVEN -> "7"
            Denomination.EIGHT -> "8"
            Denomination.NINE -> "9"
            Denomination.TEN -> "10"
            Denomination.JACK -> "J"
            Denomination.QUEEN -> "Q"
            Denomination.KING -> "K"
        }
    }

    private fun suitName(suit: Suit): String {
        return when (suit) {
            Suit.CLUB -> "클럽"
            Suit.DIAMOND -> "다이아몬드"
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
        }
    }

    private fun matchResultName(matchResult: MatchResult): String {
        return when (matchResult) {
            MatchResult.WIN -> "승"
            MatchResult.DRAW -> "무"
            MatchResult.LOSE -> "패"
        }
    }

    companion object {
        private const val READIED_PLAYERS_INFORMATION = "%s와 %s에게 2장의 카드를 나누었습니다."
        private const val HANDS_INFORMATION = "%s카드: %s"
        private const val HANDS_INFORMATION_AND_SCORE = "%s카드: %s - 결과: %s"
        private const val DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val PLAYERS_MATCH_RESULT_INTRODUCE = "## 최종 승패"
        private const val PLAYER_MATCH_RESULT = "%s: %s"
        private const val MATCH_RESULT = "%s%s "
    }
}
