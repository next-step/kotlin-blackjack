package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec

@DisplayName("트럼프 카드")
class TrumpCardTest : StringSpec({

    "카드 모양과 숫자로 생성" {
        shouldNotThrowAny {
            TrumpCard(TrumpCardShape.SPADE, TrumpCardNumber.ACE)
        }
    }
})

val SPADE_ACE: TrumpCard = TrumpCard(TrumpCardShape.SPADE, TrumpCardNumber.ACE)
val SPADE_TEN: TrumpCard = TrumpCard(TrumpCardShape.SPADE, TrumpCardNumber.TEN)
val SPADE_KING: TrumpCard = TrumpCard(TrumpCardShape.SPADE, TrumpCardNumber.KING)
val HEART_ACE: TrumpCard = TrumpCard(TrumpCardShape.HEART, TrumpCardNumber.ACE)
val DIAMOND_TWO: TrumpCard = TrumpCard(TrumpCardShape.DIAMOND, TrumpCardNumber.TWO)
val CLOVER_THREE: TrumpCard = TrumpCard(TrumpCardShape.CLOVER, TrumpCardNumber.THREE)
