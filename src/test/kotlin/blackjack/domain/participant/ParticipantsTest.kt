package blackjack.domain.participant

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class ParticipantsTest : StringSpec({
    "플레이어의 이름이 중복되면 예외를 발생한다." {
        // given
        val participants = listOf(
            Player("김경록"),
            Player("김경록"),
        )

        // when // then
        shouldThrowExactly<IllegalArgumentException> { Participants(participants) }
    }

    "첫 턴에 모든 플레이어들이 카드를 뽑는다." {
        // given
        val paritipants = Participants("김경록", "로키")
        val deck = Deck(
            mutableListOf(
                Card(Suit.SPADE, Face.TWO),
                Card(Suit.CLOVER, Face.THREE),
                Card(Suit.CLOVER, Face.FOUR),
                Card(Suit.DIAMOND, Face.FIVE),
            )
        )

        // when // then
        shouldNotThrowAny { paritipants.drawInitCards(deck) }
    }

    "차례를 기다리는 플레이어가 있을 때, 차례를 기다리는 플레이어가 존재하는지 확인한다." {
        // given
        val participants = Participants(
            listOf(
                Player(
                    "김경록",
                    Cards(
                        mutableListOf(
                            Card(Suit.DIAMOND, Face.TEN),
                            Card(Suit.DIAMOND, Face.ACE),
                        )
                    ),
                ),
                Player(
                    "로키",
                    Cards(
                        mutableListOf(
                            Card(Suit.CLOVER, Face.TWO),
                            Card(Suit.CLOVER, Face.TEN),
                        )
                    ),
                )
            )
        )

        // when
        val actual = participants.isExistWaitingPlayer()

        // then
        actual shouldBe true
    }

    "차례를 기다리는 플레이어가 없을 때, 차례를 기다리는 플레이어가 존재하는지 확인한다." {
        // given
        val participants = Participants(
            listOf(
                Player(
                    "김경록",
                    Cards(
                        mutableListOf(
                            Card(Suit.DIAMOND, Face.TEN),
                            Card(Suit.DIAMOND, Face.ACE),
                        )
                    ),
                ),
                Player(
                    "로키",
                    Cards(
                        mutableListOf(
                            Card(Suit.CLOVER, Face.EIGHT),
                            Card(Suit.CLOVER, Face.NINE),
                            Card(Suit.CLOVER, Face.TEN),
                        )
                    ),
                )
            )
        )

        // when
        val actual = participants.isExistWaitingPlayer()

        // then
        actual shouldBe false
    }

    "자신의 턴인 플레이어를 찾는 기능" {
        // given
        val participants = Participants(
            listOf(
                Player(
                    "김경록",
                    Cards(
                        mutableListOf(
                            Card(Suit.DIAMOND, Face.TEN),
                            Card(Suit.DIAMOND, Face.ACE),
                        )
                    ),
                ),
                Player(
                    "로키",
                    Cards(
                        mutableListOf(
                            Card(Suit.CLOVER, Face.EIGHT),
                            Card(Suit.CLOVER, Face.NINE),
                        )
                    ),
                )
            )
        )

        // when
        val currentTurnPlayer = participants.findCurrentTurnPlayer()

        // then
        currentTurnPlayer.name shouldBe "로키"
        assertSoftly(currentTurnPlayer.cards) {
            value[0] shouldBe Card(Suit.CLOVER, Face.EIGHT)
            value[1] shouldBe Card(Suit.CLOVER, Face.NINE)
        }
    }

    "대기중인 플레이어가 없을 때, 자신의 턴인 플레이어를 찾으면 예외를 발생시킨다." {
        // given
        val participants = Participants(
            listOf(
                Player(
                    "김경록",
                    Cards(
                        mutableListOf(
                            Card(Suit.DIAMOND, Face.TEN),
                            Card(Suit.DIAMOND, Face.ACE),
                        )
                    ),
                ),
                Player(
                    "로키",
                    Cards(
                        mutableListOf(
                            Card(Suit.CLOVER, Face.EIGHT),
                            Card(Suit.CLOVER, Face.NINE),
                            Card(Suit.CLOVER, Face.TEN),
                        )
                    ),
                )
            )
        )

        // when // then
        shouldThrowExactly<IllegalStateException> { participants.findCurrentTurnPlayer() }
    }

    "참가자들 중에서 딜러를 찾는다." {
        // given
        val participants = Participants(
            listOf(
                Player("경록"),
                Dealer(),
            )
        )

        // when
        val actual = participants.findDealer()

        // then
        actual.shouldBeInstanceOf<Dealer>()
    }

    "참가자들 중에서 플레이어들을 찾는다." {
        // given
        val participants = Participants(
            listOf(
                Player("경록"),
                Player("로키"),
                Dealer(),
            )
        )

        // when
        val actual = participants.findPlayers()

        // then
        actual.size shouldBe 2
        actual[0].shouldBeInstanceOf<Player>()
        actual[1].shouldBeInstanceOf<Player>()
    }
})
