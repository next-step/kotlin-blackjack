package blackjack.ui

import blackjack.Player
import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard

class OutputManager {

    fun printFirstTurn(players: List<Player>) {
        val names: String = players.joinToString(", ") { it.name }

        println("${names}에게 두장의 카드를 나누었습니다.")
    }

    fun printPlayersCards(players: List<Player>) {
        players.forEach {
            println("${it.name}: ${parsingCardsToString(it.cards)}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}: ${parsingCardsToString(player.cards)}")
    }

    fun printPlayerResultGame(player: Player) {
        println("${player.name} 카드: ${parsingCardsToString(player.cards)} - 결과: ${player.resultScore()}")
    }

    private fun parsingCardsToString(cards: List<BlackJackCard>): String {
        return cards.joinToString(", ") { parsingCardToString(it) }
    }

    private fun parsingCardToString(card: BlackJackCard): String {
        return when (card) {
            is NormalCard -> "${card.number}${parsingCardPatternToString(card.pattern)}"
            is PictureCard -> "${parsingCardPictureToString(card.picture)}${parsingCardPatternToString(card.pattern)}"
            is AceCard -> "ace${parsingCardPatternToString(card.pattern)}"
        }
    }

    private fun parsingCardPatternToString(cardPattern: CardPattern): String {
        return when (cardPattern) {
            CardPattern.DIAMOND -> "다이아"
            CardPattern.CLOVER -> "클로버"
            CardPattern.SPADE -> "스페이드"
            CardPattern.HEART -> "하트"
        }
    }

    private fun parsingCardPictureToString(cardPicture: CardPicture): String {
        return when (cardPicture) {
            CardPicture.KING -> "K"
            CardPicture.JACK -> "J"
            CardPicture.QUEEN -> "Q"
        }
    }
}
