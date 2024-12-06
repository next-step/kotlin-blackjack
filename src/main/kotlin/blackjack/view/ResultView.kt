package blackjack.view

import blackjack.dealer.Dealer
import blackjack.player.Player
import blackjack.player.Players
import blackjack.view.ResultView.convertToResultString

object ResultView {
    fun printPlayerNamesAndDealer(
        players: Players,
        dealer: Dealer,
    ) {
        println("${dealer.name}와 " + printPlayersName(players = players))
    }

    private fun printPlayersName(players: Players): String =
        "${players.players.joinToString { it.name }}에게 ${players.players.size}장을 나누었습니다."

    fun printPlayersCardStatus(players: Players, dealer: Dealer) {
        println("${dealer.name}카드: ${dealer.hand.cards.joinToString(
            ", ",
        ) { "${it.rank.value}${it.suit.description}" }}")

        players.players.forEach { player ->
            printPlayerCard(player)
        }
        println()
    }

    fun printPlayersCardStatusAndSum(players: Players, dealer: Dealer) {
        println()
        println("${dealer.name}카드: ${dealer.hand.cards.joinToString(
            ", ",
        ) { "${it.rank.value}${it.suit.description}" }} ${dealer.hand.sum().convertToResultString()}")
        players.players.forEach { player ->
            printPlayerCard(player, sum = player.hand.sum())
        }
    }

    fun printPlayerCard(
        player: Player,
        sum: Int? = null,
    ) {
        println(generateCardListString(player, sum))
    }

    private fun generateCardListString(
        player: Player,
        sum: Int? = null,
    ) = "${player.name}카드: ${player.hand.cards.joinToString(
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
