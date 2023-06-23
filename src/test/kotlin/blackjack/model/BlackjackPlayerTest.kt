package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("블랙잭 플레이어")
class BlackjackPlayerTest : StringSpec({

    "이름과 카드 리스트로 생성" {
        shouldNotThrowAny {
            BlackjackPlayer(
                PlayerName("name"),
                BlackjackScoreJudge,
                HandDeck(listOf(TrumpCard(TrumpCardShape.SPADE, TrumpCardNumber.ACE)))
            )
        }
    }

    "기본 인자로 빈 덱 생성" {
        BlackjackPlayer(PlayerName("name"), BlackjackScoreJudge).deck shouldBe HandDeck()
    }

    "스페이드 에이스 카드 추가" {
        // given
        val name = PlayerName("name")
        val emptyCardsPlayer = BlackjackPlayer(name, BlackjackScoreJudge)
        // when
        val addedCardPlayer: BlackjackPlayer = emptyCardsPlayer.addedCard(SPADE_ACE)
        // then
        addedCardPlayer shouldBe BlackjackPlayer(name, BlackjackScoreJudge, HandDeck(listOf(SPADE_ACE)))
    }
})
