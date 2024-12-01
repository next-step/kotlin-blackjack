package blackjack.view

import blackjack.domain.Player
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit

object ResultView {

    fun printStartCards(players: List<Player>) {
        println(buildString {
            append(players.joinToString { it.name })
            append("에게 2장의 나누었습니다.\n")
            players.forEach { append("${getPlayerInformation(it)}\n") }
        })
    }

    fun printPlayerCard(player: Player) {
        println(getPlayerInformation(player))
    }

    fun printBusted(player: Player) {
        println("${player.name}님, 버스트 되었습니다")
    }

    fun printPlayerResult(players: List<Player>) {
        println(buildString {
            players.forEach { player ->
                append("${getPlayerInformation(player)} - 결과: ${player.cards.sum()}\n")
            }
        })
    }

    private fun getPlayerInformation(player: Player): String {
        return buildString {
            append(player.name)
            append("카드: ")
            append(player.cards.cards.joinToString { "${it.rank.name()}${it.suit.name()}" })
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
