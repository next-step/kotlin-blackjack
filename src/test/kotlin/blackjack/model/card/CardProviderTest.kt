package blackjack.model.card

import blackjack.model.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 제공자 테스트")
class CardProviderTest {

    @Test
    fun `카드 제공 기능이 정상 동작`() {
        // given
        val symbols = listOf(CardSymbol("하트"), CardSymbol("클로버"))
        val numbers = listOf(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE)
        val generatedCards = CardSetGenerator.generateOneCardSet(symbols, numbers)
        val cardProvider = CardProvider(generatedCards)

        assertThat(cardProvider.cardSet.size).isEqualTo(6)

        val player = Player.from("aiden")

        // when
        cardProvider.provideOneCardTo(player)

        // then
        assertThat(cardProvider.cardSet.size).isEqualTo(5)
        assertThat(player.cardSize).isEqualTo(1)
        assertThat(player.cards.contains(Card(CardSymbol("하트"), CardNumber.ONE))).isTrue
    }
}
