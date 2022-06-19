package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSetGenerator
import blackjack.model.card.CardSymbol
import blackjack.model.card.Cards
import blackjack.model.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

@DisplayName("게임 진행자 테스트")
class GameHostTest {

    @Test
    fun `카드 제공 기능이 정상 동작`() {
        // given
        val symbols = listOf(CardSymbol.하트, CardSymbol.클로버)
        val numbers = listOf(CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR)
        val generatedCards = CardSetGenerator.generateOneCardSet(symbols, numbers)
        val gameHost = GameHost(generatedCards)

        assertThat(gameHost.cardSet.size).isEqualTo(6)

        val player = Player.from("aiden")

        // when
        gameHost.provideOneCardTo(player)

        // then
        assertAll(
            "provide one card to player test",
            { assertThat(gameHost.cardSet.size).isEqualTo(5) },
            { assertThat(player.cardSize).isEqualTo(1) },
            { assertThat(player.cards.contains(Card(CardSymbol.하트, CardNumber.TWO))).isTrue }
        )
    }

    @Test
    fun `카드 점수가 21점 이상이면 카드 제공 불가`() {
        // given
        val cards = Cards(
            mutableListOf(
                Card(CardSymbol.하트, CardNumber.TWO),
                Card(CardSymbol.클로버, CardNumber.NINE),
                Card(CardSymbol.스페이드, CardNumber.TEN),
            )
        )
        val player = Player.from("aiden", cards)
        val gameHost = GameHost()

        // when, then
        val exception = assertThrows<IllegalArgumentException> { gameHost.provideOneCardTo(player) }
        assertThat(exception.message).isEqualTo("카드 점수가 21점을 넘을 수 없습니다.")
    }
}
