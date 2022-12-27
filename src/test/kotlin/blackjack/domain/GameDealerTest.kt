package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import blackjack.model.DEFAULT_CARD_DECK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GameDealerTest {
    @Test
    fun `딜러는 총 52장의 카드 덱을 가지고 게임을 시작해야 한다`() {
        assertThat(GameDealer().deck.size).isEqualTo(52)
    }

    @Test
    fun `딜러는 게임 시작 전 카드덱을 셔플 한다`() {
        val deck = GameCardDeck(DEFAULT_CARD_DECK)
        val dealer = GameDealer(_deck = deck).apply { shuffle() }
        assertThat(dealer.deck.cards).isEqualTo(deck.cards)
    }

    @Test
    fun `딜러는 플레이어에게 카드 한장을 전달 할 수 있다`() {
        val (cardDeck, firstCard) = DEFAULT_CARD_DECK to DEFAULT_CARD_DECK.first()
        val resultCount = cardDeck.size - 1
        val dealer = GameDealer(_deck = GameCardDeck(cardDeck))
        assertThat(dealer.deliverCard()).isEqualTo(firstCard)
        assertThat(dealer.deck.size).isEqualTo(resultCount)
    }

    // Dealer GamePlay 경우 Player 동일해야 한다.
    @ParameterizedTest
    @MethodSource("provideInitialCards")
    fun `Dealer 게임 시작 전 2개의 카드를 받으면 게임 시작 상태가 된다`(cards: List<Card>) {
        val dealer = GameDealer().apply { cards.forEach(play::draw) }
        assertThat(dealer.play.cards.size).isEqualTo(cards.size)
        assertThat(dealer.play.shouldBeReadyToPlay()).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideInitialInvalidCards")
    fun `Dealer 게임 시작 전 2개의 카드를 받지 않으면 게임 시작할 수 있는 상태가 되지 않는다`(cards: List<Card>) {
        val dealer = GameDealer().apply { cards.forEach(play::draw) }
        assertThat(dealer.play.shouldBeReadyToPlay()).isFalse
    }

    @ParameterizedTest
    @MethodSource("provideHitCard")
    fun `Dealer 히트를 외치면 카드 한장을 더 받는다`(initialCards: List<Card>, hitCard: Card) {
        val dealer = GameDealer().apply {
            initialCards.forEach(play::draw)
            play.draw(hitCard)
        }
        assertThat(dealer.play.cards.size).isEqualTo(3)
    }

    @ParameterizedTest
    @MethodSource("provideBustCards")
    fun `Dealer 카드 합산이 21 초과 bust 상태가 되어 게임을 더이상 참가할 수 없다`(initialCards: List<Card>, hitCard: Card) {
        val dealer = GameDealer().apply {
            initialCards.forEach(play::draw)
            play.draw(hitCard)
        }
        assertThat(dealer.play.bust).isTrue
        assertThat(dealer.play.finished).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideNotBustCards")
    fun `Dealer 카드 합산이 21 이하 Hit 상태가 되어 게임을 계속 할 수 있다`(initialCards: List<Card>, hitCard: Card) {
        val dealer = GameDealer().apply {
            initialCards.forEach(play::draw)
            play.draw(hitCard)
        }
        assertThat(dealer.play.hit).isTrue
        assertThat(dealer.play.finished).isFalse
    }

    @Test
    fun `Dealer 최초 받은 카드가 2장의 합산이 21이면 블랙잭 완성`() {
        val cards = mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.ACE, CardShape.DIAMOND))
        val dealer = GameDealer().apply { cards.forEach(play::draw) }
        assertThat(dealer.play.blackjack).isTrue
        assertThat(dealer.play.finished).isTrue
    }

    @Test
    fun `Dealer 받은 카드가 2장 초과이고 합산이 21이면 블랙잭 완성이 아님`() {
        val cards = listOf(
            Card(CardType.THREE, CardShape.HEART),
            Card(CardType.EIGHT, CardShape.DIAMOND),
            Card(CardType.TEN, CardShape.SPADE)
        )
        val dealer = GameDealer().apply { cards.forEach(play::draw) }
        assertThat(dealer.play.bust).isFalse
    }

    // END: GamePlay

    @Test
    fun `Dealer 카드 합이 17 이상 stay 상태로 게임을 중단한다`() {
        val cards = mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.SEVEN, CardShape.DIAMOND))
        val dealer = GameDealer().apply { cards.forEach(play::draw) }
        assertThat(dealer.play.stay).isTrue
        assertThat(dealer.play.finished).isTrue
    }

    @Test
    fun `Dealer 카드 합이 16 이하 stay 상태가 아니므로 카드를 추가로 받을 수 있다`() {
        val cards = mutableListOf(Card(CardType.KING, CardShape.HEART), Card(CardType.SIX, CardShape.DIAMOND))
        val dealer = GameDealer().apply { cards.forEach(play::draw) }
        assertThat(dealer.play.stay).isFalse
        assertThat(dealer.play.finished).isFalse
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
        fun provideBustCards(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.FIVE, CardShape.CLOVER)),
                    Card(CardType.TEN, CardShape.SPADE)
                ),
                Arguments.of(
                    listOf(Card(CardType.KING, CardShape.CLOVER), Card(CardType.FOUR, CardShape.CLOVER)),
                    Card(CardType.NINE, CardShape.SPADE)
                )
            )

        @JvmStatic
        fun provideNotBustCards(): Stream<Arguments> =
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
