package blackjack.view

import betting.BetResult
import blackjack.dealer.Dealer
import blackjack.participant.Participant
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
                "${players.players.count { player -> dealer.isWin(opponent = player) }}승" +
                "${players.players.count { player -> player.isWin(opponent = dealer) }}패",
        )
        players.players.forEach {
            println(
                "${it.name}: ${it.isWin(opponent = dealer).toWinOrLoseString()}"
            )
        }
    }

    private fun Boolean.toWinOrLoseString(): String =
        if(this) {
            "승"
        } else {
            "패"
        }

    fun printBetResult(participantBets: MutableMap<Participant<*>, BetResult>) {
        println("## 최종 수익")
        participantBets.forEach { (participant, bet) ->
            println("${participant.name}: ${bet.winning.amount.toInt()}")
        }
    }
}
