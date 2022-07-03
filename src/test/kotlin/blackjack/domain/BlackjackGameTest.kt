package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import blackjack.domain.gameresult.GameResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BlackjackGameTest : StringSpec({
    "블랙잭 게임이 진행중인 상태인지 파악한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.ACE),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                false
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.NINE),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                true
            ),
        ).forEach { (cards, expected) ->
            // given
            val blackjackGame = BlackjackGame(
                deck = Deck.createOf(),
                participants = Participants(
                    listOf(
                        Player(
                            "김경록",
                            Cards(cards),
                        )
                    )
                ),
            )

            // when
            val actual = blackjackGame.isPlayerTurn()

            // then
            actual shouldBe expected
        }
    }

    "대기중인 플레이어에게 카드를 뽑을지 말지 묻는다." {
        // given
        val blackjackGame = BlackjackGame(
            deck = Deck(mutableListOf(Card(Suit.CLOVER, Face.THREE))),
            participants = Participants(
                listOf(
                    Player(
                        "김경록",
                        Cards(
                            mutableListOf(
                                Card(Suit.CLOVER, Face.NINE),
                                Card(Suit.CLOVER, Face.TEN),
                            )
                        ),
                    )
                )
            ),
        )

        // when
        blackjackGame.askDrawToCurrentTurnPlayer(true)

        // then
        blackjackGame.isPlayerTurn() shouldBe false
    }

    "딜러가 카드를 뽑을 상황인지 확인한다." {
        // given
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                false,
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SIX),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                true,
            ),
        ).forEach { (cards, expectedResult) ->
            val blackjackGame = BlackjackGame(
                deck = Deck(mutableListOf(Card(Suit.CLOVER, Face.THREE))),
                participants = Participants(
                    listOf(
                        Dealer(Cards(cards))
                    )
                ),
            )

            // when
            val actual = blackjackGame.isSatisfiedDealerPullOutCondition()

            // then
            actual shouldBe expectedResult
        }
    }

    "게임 결과를 확인한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.EIGHT),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                GameResult.LOSE,
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                GameResult.DRAW,
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SIX),
                    Card(Suit.CLOVER, Face.TEN),
                ),
                GameResult.WIN,
            ),
        ).forEach { (cards, expectedResult) ->
            val blackjackGame = BlackjackGame(
                deck = Deck(mutableListOf(Card(Suit.CLOVER, Face.THREE))),
                participants = Participants(
                    listOf(
                        Player(
                            "경록",
                            Cards(
                                mutableListOf(
                                    Card(Suit.CLOVER, Face.SEVEN),
                                    Card(Suit.CLOVER, Face.TEN),
                                )
                            )
                        ),
                        Dealer(Cards(cards)),
                    )
                ),
            )

            // when
            val actual = blackjackGame.getGameResults()

            // then
            actual[0].name shouldBe "경록"
            actual[0].result[expectedResult] shouldBe 1
            actual[1].name shouldBe "딜러"
            actual[1].result[!expectedResult] shouldBe 1
        }
    }
})
