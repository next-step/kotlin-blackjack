package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.GameResult
import blackjack.domain.Hand
import blackjack.domain.Hand.Companion.INITIAL_HAND_SIZE
import blackjack.domain.Player
import blackjack.domain.PlayerOutcome
import blackjack.domain.Rank
import blackjack.domain.Suit

object ResultView {
    private const val BUSTED = "🪦"

    fun displayState(
        game: Game,
        isInitial: Boolean = true,
    ) {
        val roster = game.players.roster
        val names = roster.map { it.name }
        val message =
            buildString {
                appendLine()
                if (isInitial) {
                    appendLine("딜러와 ${names.joinToString()}에게 2장의 나누었습니다.")
                }
                appendLine(formatDealer(game.dealer, isInitial))
                roster.forEach { appendLine(formatPlayer(it, isInitial)) }
            }
        println(message)
    }

    fun displayPlayer(player: Player) {
        println(formatPlayer(player))
    }

    fun displayDealerActions(dealer: Dealer) {
        val numberOfCardsDrawn = dealer.hand.cards.size - INITIAL_HAND_SIZE
        if (numberOfCardsDrawn == 0) {
            return
        }
        val message =
            buildString {
                appendLine()
                repeat(numberOfCardsDrawn) {
                    appendLine("딜러는 16이하라 한장의 카드를 더 받았습니다.")
                }
            }
        print(message)
    }

    fun displayResults(result: GameResult) {
        val message =
            buildString {
                appendLine("## 최종 승패")
                appendLine("딜러: ${result.dealerWins}승, ${result.dealerDraws}무, ${result.dealerLosses}패")
                result.playerResults.forEach {
                    appendLine("${it.name}: ${formatOutcome(it.outcome)}")
                }
            }
        println(message)
    }

    private fun formatOutcome(outcome: PlayerOutcome): String =
        when (outcome) {
            PlayerOutcome.WIN -> "승"
            PlayerOutcome.LOSE -> "패"
            PlayerOutcome.DRAW -> "무"
        }

    private fun formatPlayer(
        player: Player,
        isInitial: Boolean = true,
    ): String {
        val result = "${player.name}카드: ${formatHand(player.hand)}"
        if (isInitial) {
            return result
        }
        return result + " - 결과: ${if (player.isBusted) BUSTED else player.value}"
    }

    private fun formatDealer(
        dealer: Dealer,
        isInitial: Boolean,
    ): String {
        val result = "딜러: ${formatHand(dealer.hand)}"
        if (isInitial) {
            return result
        }
        return result + " - 결과: ${if (dealer.isBusted) BUSTED else dealer.value}"
    }

    private fun formatHand(hand: Hand): String = hand.cards.filter { it.isFaceUp }.joinToString { formatCard(it) }

    private fun formatCard(card: Card): String = "${formatRank(card.rank)}${formatSuit(card.suit)}"

    private fun formatRank(rank: Rank): String =
        when (rank) {
            Rank.ACE -> "A"
            Rank.TWO -> "2"
            Rank.THREE -> "3"
            Rank.FOUR -> "4"
            Rank.FIVE -> "5"
            Rank.SIX -> "6"
            Rank.SEVEN -> "7"
            Rank.EIGHT -> "8"
            Rank.NINE -> "9"
            Rank.TEN -> "10"
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
        }

    private fun formatSuit(suit: Suit): String =
        when (suit) {
            Suit.HEARTS -> "하트"
            Suit.DIAMONDS -> "다이아몬드"
            Suit.CLUBS -> "클로버"
            Suit.SPADES -> "스페이드"
        }
}
