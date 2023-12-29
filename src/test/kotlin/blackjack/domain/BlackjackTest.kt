package blackjack.domain

import io.kotest.assertions.fail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BlackjackTest {

    @Test
    fun `블랙잭 오픈 카드 공개`() {
        val dealerInitCards = listOf(Card.diamond(Number.NINE), Card.diamond(Number.JACK))
        val jasonInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val pobiInitCards = listOf(Card.heart(Number.ACE), Card.heart(Number.QUEEN))
        val cardDeck = cardDeck(dealerInitCards + jasonInitCards + pobiInitCards)
        val blackjack = BlackJack(cardDeck, "jason", "pobi")

        val actual = blackjack.openCardsOfParticipant()

        assertThat(actual)
            .containsEntry("딜러", listOf(Card.diamond(Number.NINE)))
            .containsEntry("jason", listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN)))
            .containsEntry("pobi", listOf(Card.heart(Number.ACE), Card.heart(Number.QUEEN)))
    }

    @Test
    fun `딜러 카드 얻기`() {
        val dealerInitCards = listOf(Card.diamond(Number.FIVE), Card.diamond(Number.JACK))
        val jasonInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val dealerAdditionCards = listOf(Card.diamond(Number.TWO))
        val cardDeck = cardDeck(
            dealerInitCards + jasonInitCards + dealerAdditionCards
        )
        val blackjack = BlackJack(cardDeck, "jason")

        val actual = blackjack.obtainDealerCard()

        assertThat(actual)
            .isEqualTo(listOf(Card.diamond(Number.FIVE), Card.diamond(Number.JACK), Card.diamond(Number.TWO)))
    }

    @ParameterizedTest
    @MethodSource("dealerInitCardsForCheckObtainable")
    fun `딜러 카드 획득 여부 확인`(dealerInitCards: List<Card>, expected: Boolean) {
        val jasonInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val cardDeck = cardDeck(
            dealerInitCards + jasonInitCards
        )
        val blackjack = BlackJack(cardDeck, "jason")

        val actual = blackjack.isDealerObtainable()

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("playerInitCardsForCheckObtainable")
    fun `플레이어 카드 획득 여부 확인`(playerInitCards: List<Card>, expected: Boolean) {
        val dealerInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val cardDeck = cardDeck(
            dealerInitCards + playerInitCards
        )
        val blackjack = BlackJack(cardDeck, "jason")

        val actual = blackjack.isPlayerObtainable("jason") { true }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어가 카드를 획득할 수 없으면 획득 여부도 묻지 않는다`() {
        val dealerInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val playerInitCards = listOf(Card.diamond(Number.ACE), Card.diamond(Number.JACK))
        val cardDeck = cardDeck(
            dealerInitCards + playerInitCards
        )
        val blackjack = BlackJack(cardDeck, "jason")

        blackjack.isPlayerObtainable("jason") { fail("획득 여부를 묻지 않아야 합니다.") }
    }

    @Test
    fun `플레이어 카드 얻기`() {
        val dealerInitCards = listOf(Card.diamond(Number.NINE), Card.diamond(Number.JACK))
        val jasonInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val jasonAdditionCards = listOf(Card.clover(Number.TWO))
        val cardDeck = cardDeck(
            dealerInitCards + jasonInitCards + jasonAdditionCards
        )
        val blackjack = BlackJack(cardDeck, "jason")

        val actual = blackjack.obtainPlayerCard("jason")

        assertThat(actual)
            .isEqualTo(listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN), Card.clover(Number.TWO)))
    }

    @Test
    fun `블랙잭 결과 확인`() {
        val dealerInitCards = listOf(Card.diamond(Number.NINE), Card.diamond(Number.JACK))
        val jasonInitCards = listOf(Card.clover(Number.EIGHT), Card.clover(Number.TEN))
        val pobiInitCards = listOf(Card.heart(Number.ACE), Card.heart(Number.QUEEN))
        val cardDeck = cardDeck(dealerInitCards + jasonInitCards + pobiInitCards)
        val blackjack = BlackJack(cardDeck, "jason", "pobi")

        val actual = blackjack.compareResults()

        assertThat(actual)
            .containsEntry("jason", CompareResult.DEALER_WIN)
            .containsEntry("pobi", CompareResult.DEALER_LOSE)
    }


    private fun cardDeck(cards: List<Card>): CardDeck {
        val iterator = cards.iterator()
        return CardDeck { iterator.next() }
    }

    companion object {
        @JvmStatic
        fun dealerInitCardsForCheckObtainable(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(Card.diamond(Number.SIX), Card.diamond(Number.JACK)), true),
                Arguments.of(listOf(Card.diamond(Number.SEVEN), Card.diamond(Number.JACK)), false)
            )
        }

        @JvmStatic
        fun playerInitCardsForCheckObtainable(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(Card.diamond(Number.QUEEN), Card.diamond(Number.JACK)), true),
                Arguments.of(listOf(Card.diamond(Number.ACE), Card.diamond(Number.JACK)), false)
            )
        }
    }
}
