package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

object ResultView {

    fun printStartCards(participants: List<Participant>) {
        val dealer = participants.filterIsInstance<Dealer>().firstOrNull() ?: return
        println(buildString {
            append("${dealer.name}와 ")
            append(participants.filterIsInstance<Player>().joinToString { it.name })
            append("에게 2장의 나누었습니다.\n")
            participants.forEach { append("${getPlayerInformation(it)}\n") }
        })
    }

    fun printPlayerCard(player: Player) {
        println(getPlayerInformation(player))
    }

    fun printParticipantsResult(participants: List<Participant>) {
        println(buildString {
            participants.forEach { participant ->
                append("${getPlayerInformation(participant)} - 결과: ${participant.cards.sum()}\n")
            }
        })
    }

    fun printGetCardForDealer() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(gameResult: GameResult) {
        println(buildString {
            append("## 최종 승패\n")
            append("${gameResult.dealerResult.dealer.name}: ${gameResult.dealerResult.winCount}승 ${gameResult.dealerResult.loseCount}패\n")
            gameResult.playersResult.forEach {
                append("${it.player.name}: ${if (it.isWin) "승" else "패"}\n")
            }
        })
    }

    private fun getPlayerInformation(participant: Participant): String {
        return buildString {
            append(participant.name)
            append("카드: ")
            append(participant.cards.cards.joinToString { "${it.rank.name()}${it.suit.name()}" })
        }
    }

    private fun CardSuit.name(): String = when (this) {
        CardSuit.Diamond -> "다이아몬드"
        CardSuit.Heart -> "하트"
        CardSuit.Spade -> "스페이드"
        CardSuit.Club -> "클로버"
    }

    private fun CardRank.name(): String = when (this) {
        CardRank.Ace -> "A"
        CardRank.Two -> "2"
        CardRank.Three -> "3"
        CardRank.Four -> "4"
        CardRank.Five -> "5"
        CardRank.Six -> "6"
        CardRank.Seven -> "7"
        CardRank.Eight -> "8"
        CardRank.Nine -> "9"
        CardRank.Ten -> "10"
        CardRank.Jack -> "J"
        CardRank.Queen -> "Q"
        CardRank.King -> "k"
    }

}
