package blackjack.io

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.Suit
import blackjack.domain.User
import blackjack.domain.Users

object ResultView {
    private const val DECK_INITIALIZED_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val DEALER_DECK_PRINT_FORMAT = "딜러: %s"
    private const val DECK_PRINT_FORMAT = "%s카드: %s"
    private const val DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_RESULT_PRINT_FORMAT = "딜러 카드: %s - 결과: %s"
    private const val RESULT_PRINT_FORMAT = "%s카드: %s - 결과: %s"

    fun printDecks(dealerDeck: Deck, users: Users) {
        val message = buildString {
            appendLine()
            appendLine(getDeckInitializedMessage(users))
            appendLine(getDealerDeckString(dealerDeck))
            appendLine(getUsersDeckString(users))
        }
        print(message)
    }

    private fun getDeckInitializedMessage(users: Users): String {
        return DECK_INITIALIZED_FORMAT.format(users.joinToString(", ") { it.name })
    }

    private fun getDealerDeckString(deck: Deck): String {
        return DEALER_DECK_PRINT_FORMAT.format(deckToString(deck))
    }

    private fun getUsersDeckString(users: Users): StringBuilder {
        val stringBuilder = StringBuilder()
        for (user in users) {
            stringBuilder.appendLine(getUserDeckString(user))
        }
        return stringBuilder
    }

    private fun getUserDeckString(user: User): String {
        return DECK_PRINT_FORMAT.format(user.name, deckToString(user.deck))
    }

    fun printUserDeck(user: User) {
        println(getUserDeckString(user))
    }

    fun printResult(dealerDeck: Deck, users: Users) {
        println()
        println(dealerResultFormatting(dealerDeck))
        for (user in users) {
            println(userResultFormatting(user))
        }
    }

    private fun dealerResultFormatting(dealerDeck: Deck): String {
        return DEALER_RESULT_PRINT_FORMAT.format(
            deckToString(dealerDeck),
            dealerDeck.sum(),
        )
    }

    private fun userResultFormatting(user: User): String {
        return RESULT_PRINT_FORMAT.format(
            user.name,
            deckToString(user.deck),
            user.calculatePoint(),
        )
    }

    private fun cardNumberToString(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.ACE -> "A"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            else -> (cardNumber.ordinal + 1).toString()
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
