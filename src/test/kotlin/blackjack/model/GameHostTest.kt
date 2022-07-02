package blackjack.model

import blackjack.model.candidate.Player
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSetGenerator
import blackjack.model.card.CardSymbol
import blackjack.model.card.Cards
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

        val player = Player.from("aiden", 1)

        // when
        gameHost.provideOneCardTo(player)

        // then
        val expectedGameHostCards = Cards(
            mutableListOf(
                Card(CardSymbol.하트, CardNumber.THREE),
                Card(CardSymbol.하트, CardNumber.FOUR),
                Card(CardSymbol.클로버, CardNumber.TWO),
                Card(CardSymbol.클로버, CardNumber.THREE),
                Card(CardSymbol.클로버, CardNumber.FOUR)
            )
        )

        val expectedPlayerCards = Cards(mutableListOf(Card(CardSymbol.하트, CardNumber.TWO)))

        assertAll(
            "provide one card to candidate test",
            { assertThat(gameHost.cardSet).isEqualTo(expectedGameHostCards) },
            { assertThat(player.cards).isEqualTo(expectedPlayerCards) }
        )
    }

    @Test
    fun `카드 점수가 21점 이상이면 카드 제공 불가`() {
        // given
        val player = Player.from("aiden", 1)
        player.receiveCard(Card(CardSymbol.하트, CardNumber.TWO))
        player.receiveCard(Card(CardSymbol.클로버, CardNumber.NINE))
        player.receiveCard(Card(CardSymbol.스페이드, CardNumber.TEN))

        val gameHost = GameHost()

        // when, then
        val exception = assertThrows<IllegalArgumentException> { gameHost.provideOneCardTo(player) }
        assertThat(exception.message).isEqualTo("카드 점수가 21점을 넘을 수 없습니다.")
    }
}
