package blackjack.view

import blackjack.entity.Card
import blackjack.entity.Dealer
import blackjack.entity.GameResult
import blackjack.entity.Hand
import blackjack.entity.Participants
import blackjack.entity.Player
import blackjack.entity.Rank.ACE
import blackjack.entity.Rank.EIGHT
import blackjack.entity.Rank.FIVE
import blackjack.entity.Rank.FOUR
import blackjack.entity.Rank.JACK
import blackjack.entity.Rank.KING
import blackjack.entity.Rank.NINE
import blackjack.entity.Rank.QUEEN
import blackjack.entity.Rank.SEVEN
import blackjack.entity.Rank.SIX
import blackjack.entity.Rank.TEN
import blackjack.entity.Rank.THREE
import blackjack.entity.Rank.TWO
import blackjack.entity.Suit

class OutputView {
    fun printInitialHands(participants: Participants) {
        val dealer = participants.dealer
        println("${dealer.name}와 ${participants.players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        println("${dealer.name}: ${formatHand(dealer.hand)}")
        participants.players.forEach {
            println("${it.name}: ${formatHand(it.hand)}")
        }
        println()
    }

    fun printPlayerHand(player: Player) {
        println("${player.name}카드: ${formatHand(player.hand)}\n")
    }

    fun printPlayerBusted(player: Player) {
        println("${player.name}님의 카드 합이 21을 초과했습니다. 턴을 종료합니다.\n")
    }

    fun printDealerDrawCard(dealer: Dealer) {
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printPlayerBlackjack(player: Player) {
        println("${player.name}님은 블랙잭입니다. 게임을 종료합니다.\n")
    }

    fun printPlayerResults(
        playerName: String,
        playerHand: Hand,
        score: Int,
    ) {
        println("${playerName}카드: ${formatHand(playerHand)} - 결과: $score")
    }

    fun printGameResult(gameResults: List<GameResult>) {
        println("\n## 최종 승패")
        gameResults.forEach { result ->
            val resultText =
                if (result.player is Dealer) {
                    formatDealerResult(result)
                } else {
                    formatPlayerResult(result)
                }
            println("${result.player.name}: $resultText")
        }
    }

    private fun formatDealerResult(result: GameResult): String =
        buildString {
            if (result.wins > 0) append("${result.wins}승 ")
            if (result.loses > 0) append("${result.loses}패 ")
            if (result.draws > 0) append("${result.draws}무")
        }.trim()

    private fun formatPlayerResult(result: GameResult): String =
        when {
            result.wins > 0 -> "승"
            result.loses > 0 -> "패"
            else -> "무"
        }

    private fun formatHand(hand: Hand): String {
        return hand.cards
            .joinToString(", ") { it.name() }
    }

    private fun Card.name() = "${rankDisplay[rank]}${suitDisplay[suit]}"

    companion object {
        private const val SPADE_DISPLAY = "스페이드"
        private const val HEART_DISPLAY = "하트"
        private const val DIAMOND_DISPLAY = "다이아몬드"
        private const val CLUB_DISPLAY = "클로버"

        private const val ACE_DISPLAY = "A"
        private const val JACK_DISPLAY = "J"
        private const val QUEEN_DISPLAY = "Q"
        private const val KING_DISPLAY = "K"

        private val rankDisplay =
            mapOf(
                ACE to ACE_DISPLAY,
                TWO to "2",
                THREE to "3",
                FOUR to "4",
                FIVE to "5",
                SIX to "6",
                SEVEN to "7",
                EIGHT to "8",
                NINE to "9",
                TEN to "10",
                JACK to JACK_DISPLAY,
                QUEEN to QUEEN_DISPLAY,
                KING to KING_DISPLAY,
            )

        private val suitDisplay =
            mapOf(
                Suit.SPADES to SPADE_DISPLAY,
                Suit.HEARTS to HEART_DISPLAY,
                Suit.DIAMONDS to DIAMOND_DISPLAY,
                Suit.CLUBS to CLUB_DISPLAY,
            )
    }
}
