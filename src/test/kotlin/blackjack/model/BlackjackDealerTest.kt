package blackjack.model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("블랙잭 딜러")
class BlackjackDealerTest : StringSpec({

    "카드 덱과 카드 선택 방법으로 생성" {
        shouldNotThrowAny {
            BlackjackDealer(emptyList()) { cards -> cards.first() }
        }
    }

    "덱에서 카드를 가져오면서 카드 제외" {
        // given
        val cardSelector: (CardDeck) -> TrumpCard = { cards -> cards.first() }
        val dealer = BlackjackDealer(listOf(SPADE_ACE), cardSelector)
        // when
        val drawnCard: TrumpCard = dealer.drawCardAndRemoved()
        // then
        assertSoftly {
            drawnCard shouldBe SPADE_ACE
            dealer shouldBe BlackjackDealer(emptyList(), cardSelector)
        }
    }
})
