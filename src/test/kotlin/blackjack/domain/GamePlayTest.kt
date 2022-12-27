package blackjack.domain

import blackjack.domain.Finished.Blackjack
import blackjack.domain.Finished.Bust
import blackjack.domain.Finished.Stay
import blackjack.domain.Playing.Hit
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GamePlayTest {

    @Test
    fun `블랙잭 상태 확인`() {
        val play = GamePlay(Blackjack())
        assertThat(play.blackjack).isTrue
    }

    @Test
    fun `히트 상태 확인`() {
        val play = GamePlay(Hit())
        assertThat(play.hit).isTrue
    }

    @Test
    fun `스테이 상태 확인`() {
        val play = GamePlay(Stay())
        assertThat(play.stay).isTrue
    }

    @Test
    fun `버스트 상태 확인`() {
        val play = GamePlay(Bust())
        assertThat(play.bust).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideFinishedStates")
    fun `종료 상태 확인`(state: State) {
        val play = GamePlay(state)
        assertThat(play.finished).isTrue
    }

    @ParameterizedTest
    @MethodSource("provideNotFinishedStates")
    fun `비종료 상태 확인`(state: State) {
        val play = GamePlay(state)
        assertThat(play.finished).isFalse
    }

    @Test
    fun `현재 점수 확인`() {
        val cards = Cards(
            listOf(
                Card(CardType.ACE, CardShape.CLOVER),
                Card(CardType.TEN, CardShape.CLOVER)
            )
        )
        val play = GamePlay(Blackjack(cards))
        assertThat(play.score()).isEqualTo(cards.sum())
    }

    @Test
    fun `최초 2장의 카드를 받으면 게임 준비 상태`() {
        val cards = listOf(
            Card(CardType.NINE, CardShape.CLOVER),
            Card(CardType.TEN, CardShape.CLOVER)
        )
        val play = GamePlay().apply { cards.forEach(::draw) }
        assertThat(play.shouldBeReadyToPlay()).isTrue
    }

    @Test
    fun `최초 총합이 21미만인 2장의 카드를 받으면 히트 상태`() {
        val cards = listOf(
            Card(CardType.NINE, CardShape.CLOVER),
            Card(CardType.TEN, CardShape.CLOVER)
        )
        val play = GamePlay().apply { cards.forEach(::draw) }
        assertThat(play.hit).isTrue
    }

    @Test
    fun `최초 총합이 21인 2장의 카드를 받으면 블랙잭 상태`() {
        val cards = listOf(
            Card(CardType.ACE, CardShape.CLOVER),
            Card(CardType.TEN, CardShape.CLOVER)
        )
        val play = GamePlay().apply { cards.forEach(::draw) }
        assertThat(play.blackjack).isTrue
    }

    @Test
    fun `히트 상태에서 한장의 카드를 더 받은 후의 합이 21 이하이면 히트 상태`() {
        val hit = Hit(
            cards = Cards(
                initialValue = listOf(
                    Card(CardType.EIGHT, CardShape.CLOVER),
                    Card(CardType.TEN, CardShape.CLOVER)
                )
            )
        )
        val play = GamePlay(hit).apply {
            draw(Card(CardType.TWO, CardShape.CLOVER))
        }
        assertThat(play.hit).isTrue
    }

    @Test
    fun `히트 상태에서 한장의 카드를 더 받은 후의 합이 21 초과이면 버스트 상태`() {
        val hit = Hit(
            cards = Cards(
                initialValue = listOf(
                    Card(CardType.EIGHT, CardShape.CLOVER),
                    Card(CardType.TEN, CardShape.CLOVER)
                )
            )
        )
        val play = GamePlay(hit).apply {
            draw(Card(CardType.FIVE, CardShape.CLOVER))
        }
        assertThat(play.bust).isTrue
    }

    companion object {
        @JvmStatic
        fun provideFinishedStates(): Stream<State> = Stream.of(Blackjack(), Bust(), Stay())

        @JvmStatic
        fun provideNotFinishedStates(): Stream<State> = Stream.of(Started(), Hit())
    }
}
