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

    @Test
    fun `게임이 시작되고, 카드 배포를 요청할 때, 카드팩의 카드가 반환된다`() {
        // given : 카드팩 생성, 게임이 시작된다.
        val cardPack = CardPack.getCardPack()
        val game = BlackjackGame(cardPack)

        // when : 카드 배포를 한다.
        val actual = game.hit()
        val expect = cardPack.cardList[0]

        // then : 첫번째 카드팩이 반환된다.
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `게임이 시작되고, 카드 배포를 반복할 때, 동일하지 않은 카드를 배포한다`() {
        // given : 카드팩 생성, 게임 시작
        val cardPack = CardPack.getCardPack()
        val game = BlackjackGame(cardPack)

        // when : 카드 배포 2회한다.
        val card1 = game.hit()
        val card2 = game.hit()

        // then : 동일하지 않은 카드가 배포된다.
        assertThat(card1).isNotEqualTo(card2)
    }
}
