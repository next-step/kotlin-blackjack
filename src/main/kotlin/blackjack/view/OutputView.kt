package blackjack.view

import blackjack.model.BlackjackPlayer
import blackjack.model.BlackjackPlayerConsumer
import blackjack.model.BlackjackPlayers
import blackjack.model.BlackjackPlayersCardCountConsumer
import blackjack.model.BlackjackPlayersScoreConsumer
import blackjack.model.TrumpCard
import blackjack.model.TrumpCardNumber
import blackjack.model.TrumpCardShape

object OutputView : BlackjackPlayerConsumer, BlackjackPlayersCardCountConsumer, BlackjackPlayersScoreConsumer {

    override fun consumePlayersCardCount(players: BlackjackPlayers, cardCount: Int) {
        println("${players.players.joinToString { it.name.toString() }}에게 ${cardCount}장의 나누었습니다.")
        players.players.forEach { consumePlayer(it) }
        println()
    }

    override fun consumePlayer(player: BlackjackPlayer) {
        println(playerString(player))
    }

    override fun consumePlayers(players: BlackjackPlayers) {
        players.players.forEach {
            println("${playerString(it)} - 결과: ${it.deckScore}")
        }
    }

    private fun playerString(player: BlackjackPlayer): String {
        return "${player.name}카드: ${player.deck.cards.joinToString { cardString(it) }}"
    }

    private fun cardString(card: TrumpCard): String {
        return "${cardNumberString(card.number)}${cardShapeString(card.shape)}"
    }

    private fun cardShapeString(shape: TrumpCardShape) = when (shape) {
        TrumpCardShape.SPADE -> "스페이드"
        TrumpCardShape.HEART -> "하트"
        TrumpCardShape.DIAMOND -> "다이아몬드"
        TrumpCardShape.CLOVER -> "클로버"
    }

    private fun cardNumberString(number: TrumpCardNumber): String = when (number) {
        TrumpCardNumber.ACE -> "A"
        TrumpCardNumber.TWO -> "2"
        TrumpCardNumber.THREE -> "3"
        TrumpCardNumber.FOUR -> "4"
        TrumpCardNumber.FIVE -> "5"
        TrumpCardNumber.SIX -> "6"
        TrumpCardNumber.SEVEN -> "7"
        TrumpCardNumber.EIGHT -> "8"
        TrumpCardNumber.NINE -> "9"
        TrumpCardNumber.TEN -> "10"
        TrumpCardNumber.JACK -> "J"
        TrumpCardNumber.QUEEN -> "Q"
        TrumpCardNumber.KING -> "K"
    }
}
