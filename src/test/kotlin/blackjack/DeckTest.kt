package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DeckTest : StringSpec() {
    private lateinit var deck: Deck
    init {
        beforeEach {
            deck = Deck()
        }

        "최초로 게임을 시작했을 때 52장의 카드 덱을 가진다." {
            deck.values.values.size shouldBe 52
        }

        "카드 덱은 중복 없는 플레잉 카드를 가진다." {
            deck.values.values shouldBe Card.ALL_CARDS
        }

        "52장의 카드 중에서 한 장을 뽑으면 카드 덱의 사이즈는 1 줄어든다." {
            // given
            deck.values.values.size shouldBe 52

            // when
            deck.draw()

            // then
            deck.values.values.size shouldBe 51
        }

        "플레이어들에게 최초로 지급되는 카드는 2장이다." {
            // given
            // when
            val result = deck.drawInitCards()

            // then
            result.values.size shouldBe 2
        }
    }
}
