package blackJack

import BlackjackGame
import card.CardPack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `정상적인 카드팩을 받고, 게임에 주입되었을 때, 예외 검증을 통과한다`() {
        // given : 정상적인 카드팩을 받는다.
        val cardPack = CardPack.getCardPack()

        // when : 게임에 카드를 주입한다.
        val actual = runCatching { BlackjackGame(cardPack) }.exceptionOrNull()

        // then : 예외를 던지지 않는다.
        assertThat(actual).isNull()
    }
}
