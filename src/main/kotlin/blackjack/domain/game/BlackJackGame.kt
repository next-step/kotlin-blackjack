package blackjack.domain.game

import blackjack.domain.card.CardScoreNormalAcePolicy
import blackjack.domain.card.CardScorePolicyGroup
import blackjack.domain.card.CardScoreSpecialAcePolicy
import blackjack.domain.player.PlayerGroup
import blackjack.domain.player.PlayerScore

class BlackJackGame(
    val playerGroup: PlayerGroup,
    private val cardDealer: CardDealer = RandomCardDealer(),
    private val cardScorePolicyGroup: CardScorePolicyGroup = CardScorePolicyGroup(
        listOf(
            CardScoreSpecialAcePolicy,
            CardScoreNormalAcePolicy
        )
    )
) {
    fun dealCard(count: Int): BlackJackGame {
        val playerGroup = PlayerGroup(
            playerGroup.players.map {
                it.receiveCard(cardDealer.selectCard(count))
            }
        )
        return BlackJackGame(playerGroup, cardDealer)
    }

    fun start(): BlackJackGame {
        return dealCard(START_CARD_COUNT)
    }

    fun end(): BlackJackGameResult {
        return BlackJackGameResult(playerGroup.players.map {
            PlayerScore(
                it,
                it.cardSet.sumOfBest(cardScorePolicyGroup, GOAL_SCORE)
            )
        })
    }

    companion object {
        private const val START_CARD_COUNT = 2
        private const val GOAL_SCORE = 21
    }
}
