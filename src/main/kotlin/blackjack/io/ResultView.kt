package blackjack.io

import blackjack.domain.AceCardNumber
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.JackQueenKingCardNumber
import blackjack.domain.NumberCardNumber
import blackjack.domain.Suit
import blackjack.domain.User
import blackjack.domain.Users

object ResultView {
    private const val DECK_PRINT_FORMAT = "%s카드: %s"
    private const val RESULT_PRINT_FORMAT = "%s카드: %s - 결과: %s"
    private const val GAME_OVER_MESSAGE = "게임오버"

    fun printUsersDeck(users: Users) {
        println()
        for (user in users) {
            printUserDeck(user)
        }
        println()
    }

    fun printUserDeck(user: User) {
        println(DECK_PRINT_FORMAT.format(user.name, deckToString(user.deck)))
    }

    fun printUsersResult(users: Users) {
        println()
        for (user in users) {
            println(resultFormatting(user))
        }
    }

    private fun resultFormatting(user: User): String {
        return RESULT_PRINT_FORMAT.format(
            user.name,
            deckToString(user.deck),
            user.calculatePointOrNull() ?: GAME_OVER_MESSAGE,
        )
    }

    private fun cardNumberToString(cardNumber: CardNumber): String {
        return when (cardNumber) {
            is AceCardNumber -> "A"
            is NumberCardNumber -> cardNumber.number.toString()
            is JackQueenKingCardNumber -> {
                when (cardNumber.number) {
                    JackQueenKingCardNumber.JACK_NUMBER -> "J"
                    JackQueenKingCardNumber.QUEEN_NUMBER -> "Q"
                    JackQueenKingCardNumber.KING_NUMBER -> "K"
                    else -> throw IllegalStateException()
                }
            }
        }
    }

    private fun suitToString(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.HEART -> "하트"
            Suit.CLOVER -> "클로버"
        }
    }

    private fun cardToString(card: Card): String {
        return cardNumberToString(card.cardNumber) + suitToString(card.suit)
    }

    private fun deckToString(deck: Deck): String {
        return deck.joinToString(", ", transform = ::cardToString)
    }
}
