package blackjack.view

import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Player
import blackjack.player.Players

object ResultView {
    fun printPlayerNamesAndDealer(
        players: Players,
        dealer: Dealer,
    ) {
        println("${dealer.name}와 " + printPlayersName(players = players))
    }

    private fun printPlayersName(players: Players): String =
        "${players.players.joinToString { it.name }}에게 ${players.players.size}장을 나누었습니다."

    fun <T : Participant<T>> printPlayersCardStatus(participants: List<T>) {
        participants.forEach { participant ->
            printPlayerCard(participant)
        }
        println()
    }

    fun <T : Participant<T>> printPlayersCardStatusAndSum(participant: List<T>) {
        println()
        participant.forEach { player ->
            printPlayerCard(player, sum = player.hand.sum())
        }
    }

    fun <T : Participant<T>> printPlayerCard(
        participant: T,
        sum: Int? = null,
    ) {
        println(generateCardListString(participant, sum))
    }

    private fun <T : Participant<T>> generateCardListString(
        participant: T,
        sum: Int? = null,
    ) = "${participant.name}카드: ${participant.hand.cards.joinToString(
        ", ",
    ) { "${it.rank.value}${it.suit.description}" }} ${sum?.convertToResultString() ?: ""}"

    private fun Int.convertToResultString() = "- 결과: $this"

    fun printDealerDrawCard() {
        println("딜러는 ${Dealer.DEALER_STANDING_RULE}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printWinner(players: Players, dealer: Dealer) {
        println("## 최종 승패")

        println(
            "${dealer.name}: " +
                "${players.players.count { isDealerWin(player = it, dealer = dealer) }}승" +
                "${players.players.count { isPlayerWin(player = it, dealer = dealer) }}패",
        )
        players.players.forEach {
            println(
                "${it.name}: ${isPlayerWin(player = it, dealer = dealer).toWinOrLoseString()}"
            )
        }
    }

    private fun isDealerWin(player: Player, dealer: Dealer): Boolean =
        when {
            dealer.isBust() -> false
            player.isBust() -> true
            else -> dealer.hand.sum() > player.hand.sum()
        }

    private fun isPlayerWin(player: Player, dealer: Dealer): Boolean =
        when {
            player.isBust() -> false
            dealer.isBust() -> true
            else -> player.hand.sum() > dealer.hand.sum()
        }

    private fun Boolean.toWinOrLoseString(): String =
        if(this) {
            "승"
        } else {
            "패"
        }
}
