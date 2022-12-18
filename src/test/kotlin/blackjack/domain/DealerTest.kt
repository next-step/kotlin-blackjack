package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import blackjack.model.DEFAULT_CARD_DECK
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DealerTest {
    @Test
    fun `딜러는 총 52장의 카드 덱을 가지고 게임을 시작해야 한다`() {
        assertThat(Dealer().deck.size).isEqualTo(52)
    }

    @Test
    fun `딜러는 게임 시작 전 카드덱을 셔플 한다`() {
        val deck = CardDeckImpl(DEFAULT_CARD_DECK)
        val dealer = Dealer(deck).apply { shuffle() }
        assertThat(dealer.deck.cards).isEqualTo(deck.cards)
    }

    @Test
    fun `딜러는 플레이어에게 카드 한장을 전달 할 수 있다`() {
        val (cardDeck, firstCard) = DEFAULT_CARD_DECK to DEFAULT_CARD_DECK.first()
        val resultCount = cardDeck.size - 1
        val dealer = Dealer(CardDeckImpl(cardDeck))
        assertThat(dealer.deliverCard()).isEqualTo(firstCard)
        assertThat(dealer.deck.size).isEqualTo(resultCount)
    }

    // Dealer GamePlay 경우 Player 동일해야 한다.
    @ParameterizedTest
    @MethodSource("provideInitialCards")
    fun `Dealer 게임 시작 전 2개의 카드를 받는다`(cards: List<Card>) {
        val player = Player("고니").apply { readyToPlay(cards) }
        assertThat(player.cards.size).isEqualTo(cards.size)
    }

    @ParameterizedTest
    @MethodSource("provideInitialInvalidCards")
    fun `Dealer 게임 시작 전 2개의 카드를 받지 않으면 에러가 발생한다`(cards: List<Card>) {
        val dealer = Dealer()
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { dealer.readyToPlay(cards) }
    }

    @ParameterizedTest
    @MethodSource("provideHitCard")
    fun `Dealer 히트를 외치면 카드 한장을 더 받는다`(initialCards: List<Card>, hitCard: Card) {
        val dealer = Dealer().apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(dealer.cards.value.last()).isEqualTo(hitCard)
    }

    @ParameterizedTest
    @MethodSource("provideBurstCards")
    fun `Dealer 카드 합산이 21 초과 burst 상태가 되어 게임을 더이상 참가할 수 없다`(initialCards: List<Card>, hitCard: Card) {
        val dealer = Dealer().apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(dealer.burst()).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideNotBurstCards")
    fun `Dealer 카드 합산이 21 이상일 떄 burst 상태가 되어 게임을 계속 할 수있다`(initialCards: List<Card>, hitCard: Card) {
        val dealer = Dealer().apply {
            readyToPlay(initialCards)
            hit(hitCard)
        }
        assertThat(dealer.burst()).isFalse
    }

    @Test
    fun `Dealer 카드가 2장이고 합산이 21이면 블랙잭 완성`() {
        val cards = Cards(mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.ACE, CardShape.DIAMOND)))
        val dealer = Dealer(cards = cards)
        assertThat(dealer.blackjack()).isTrue
    }

    @Test
    fun `Dealer 카드가 2장 초과이고 합산이 21이면 블랙잭 완성이 아님`() {
        val cards = Cards(
            listOf(
                Card(CardType.THREE, CardShape.HEART),
                Card(CardType.EIGHT, CardShape.DIAMOND),
                Card(CardType.TEN, CardShape.SPADE)
            )
        )
        val dealer = Dealer(cards = cards)
        assertThat(dealer.blackjack()).isFalse
    }

    // END: GamePlay

    @Test
    fun `Dealer 카드 합이 17이상이면 stay 상태로 게임을 중단한다`() {
        val cards = mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.SEVEN, CardShape.DIAMOND))
        val dealer = Dealer().apply { readyToPlay(cards) }
        assertThat(dealer.stay()).isTrue
    }

    @Test
    fun `Dealer 카드 합이 17이상이면 stay 상태가 아니므로 카드를 추가로 받을 수 있다`() {
        val cards = mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.SIX, CardShape.DIAMOND))
        val dealer = Dealer().apply { readyToPlay(cards) }
        assertThat(dealer.stay()).isFalse
    }

    companion object {
        @JvmStatic
        fun provideInitialCards(): Stream<List<Card>> =
            Stream.of(listOf(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.ACE, CardShape.CLOVER)))

        @JvmStatic
        fun provideInitialInvalidCards(): Stream<List<Card>> =
            Stream.of(listOf(Card(CardType.JACK, CardShape.CLOVER)), listOf())

        @JvmStatic
        fun provideHitCard(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.THREE, CardShape.CLOVER), Card(CardType.EIGHT, CardShape.HEART)),
                    Card(CardType.JACK, CardShape.DIAMOND)
                )
            )

        @JvmStatic
        fun provideBurstCards(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.QUEEN, CardShape.CLOVER)),
                    Card(CardType.TWO, CardShape.SPADE)
                ),
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.QUEEN, CardShape.CLOVER)),
                    Card(CardType.NINE, CardShape.SPADE)
                )
            )

        @JvmStatic
        fun provideNotBurstCards(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.FIVE, CardShape.CLOVER)),
                    Card(CardType.ACE, CardShape.SPADE)
                ),
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.TWO, CardShape.CLOVER)),
                    Card(CardType.FOUR, CardShape.SPADE)
                )
            )
    }
}
