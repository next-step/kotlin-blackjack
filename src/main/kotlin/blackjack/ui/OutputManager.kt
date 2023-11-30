package blackjack.ui

import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import blackjack.participant.AbstractPlayer
import blackjack.participant.Player

class OutputManager {
    fun printPlayersCards(players: List<AbstractPlayer>) {
        players.forEach {
            if (it.isDealer()) {
                println("${it.name}: ${parsingCardsToString(it.cards.first())}")
                return@forEach
            }
            println("${it.name}: ${parsingCardsToString(it.cards)}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}: ${parsingCardsToString(player.cards)}")
    }

    fun printPlayerCards(player: AbstractPlayer) {
        println("${player.name}: ${parsingCardsToString(player.cards)}")
    }

    fun printPlayerResultGame(player: AbstractPlayer) {
        println("${player.name} 카드: ${parsingCardsToString(player.cards)} - 결과: ${player.resultScore()}")
    }

    private fun parsingCardsToString(cards: List<BlackJackCard>): String {
        return cards.joinToString(", ") { parsingCardToString(it) }
    }

    private fun parsingCardsToString(cards: BlackJackCard): String {
        return parsingCardToString(cards)
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

    fun printFirstTurn(players: List<AbstractPlayer>) {
        val names: String = players.joinToString(", ") { it.name }

        println("${names}에게 2장의 카드를 나누었습니다.")
    }
}
