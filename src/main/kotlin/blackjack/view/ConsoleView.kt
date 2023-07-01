package blackjack.view

import blackjack.Card
import blackjack.CardDrawCommand
import blackjack.CardNumber
import blackjack.Player
import blackjack.Suit
import blackjack.dto.PlayerGameResult

object ConsoleView : InputView, OutputView {
    override fun fetchPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",")
        println()
        return names
    }

    override fun fetchCardDrawCommand(player: Player): CardDrawCommand {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            "y" -> CardDrawCommand.YES
            "n" -> CardDrawCommand.NO
            else -> throw IllegalArgumentException("잘못된 입력입니다.")
        }
    }

    override fun showInitialStatus(players: List<Player>) {
        players.forEach {
            println("${it.name}에게 2장의 나누었습니다.")
            println("${it.name}카드: ${serialize(it.currentCards)}")
        }
        println()
    }

    override fun showCurrentStatusOf(player: Player) {
        println("${player.name}카드: ${serialize(player.currentCards)}")
    }

    override fun showGameResult(playerGameResults: List<PlayerGameResult>) {
        println()
        playerGameResults.forEach {
            println("${it.name}카드: ${serialize(it.cards)} - 결과: ${it.score}")
        }
    }

    private fun serialize(card: List<Card>): String {
        return card.joinToString(", ")
        { "${it.number.serialize()}${it.suit.serialize()}" }
    }

    private fun CardNumber.serialize(): String {
        return when (this) {
            CardNumber.ACE -> "A"
            CardNumber.TWO -> "2"
            CardNumber.THREE -> "3"
            CardNumber.FOUR -> "4"
            CardNumber.FIVE -> "5"
            CardNumber.SIX -> "6"
            CardNumber.SEVEN -> "7"
            CardNumber.EIGHT -> "8"
            CardNumber.NINE -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
        }
    }

    private fun Suit.serialize(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLOVER -> "클로버"
            Suit.HEART -> "하트"
        }
    }
}
