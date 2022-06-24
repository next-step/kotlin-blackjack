package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어의 이름이 빈칸 혹은 공백이면 예외를 발생한다." {
        listOf(
            " ",
            ""
        ).forEach {
            // when //then
            shouldThrowExactly<IllegalArgumentException> { Player(it) }
        }
    }

    "플레이어가 카드들을 뽑는다." {
        // given
        val player = Player("김경록")

        // when
        player.drawCards(
            listOf(
                Card(Suit.CLOVER, Face.ACE),
                Card(Suit.SPADE, Face.TWO),
            )
        )

        // then
        player.cards.size shouldBe 2
    }
})
